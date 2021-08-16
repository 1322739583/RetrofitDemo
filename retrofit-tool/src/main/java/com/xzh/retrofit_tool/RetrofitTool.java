package com.xzh.retrofit_tool;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RetrofitTool {
    private static String getUrl = "http://www.githun.com/repo/{user}/{id}";
    private static String getUrl2 = "http://www.githun.com/repos/{user}/{repo}/releases/{id}/assets?type=xxx&time=xxx";
    private static String getUrl3 = "http://www.githun.com/repos/{user}/{repo}/releases/{id}";

    private static File file;
    private int pathListSize;
    private int queryListSize;
    private boolean isPost = false;

    private String funcName;


    private String requestType = "GET";
    //不需要构造函数，url的值在声明多个变量的时候会被重置
//    public RetrofitTool(String url) {
//      //this.url=url;
//    }

    /**
     * 判断是不是path是没有什么意义的，这个在文档里面就应该写上的，是restful的规范
     *
     * @param url
     * @return
     * @Deprecated 用getPathByRestful
     */
    @Deprecated
    public List<String> getPath(String url) {
        //这样写比较保险，可以防止http://www.xxx.com/user/add.action?id=xxx这种情况下出现错误
        int index = url.indexOf('.');//先把开头的http://www去掉
        String substring = url.substring(index);
        int index2 = substring.indexOf("/");

        //去除第一个'/'
        String path = substring.substring(index2 + 1);
        List<String> pathList = Splitter.on("/").splitToList(path);
        //   System.out.println("the list is:"+pathList.toString());
        //获取最后一个元素
        //   System.out.println(pathList.get(pathList.size()-1));

        return pathList;
    }

    /**
     * 根据符合RestFul的API获取path
     *
     * @param url
     * @return
     */
    public List<String> getPathByRestful(String url) {

        List<String> pathList = new ArrayList<>();
        List<String> list = Splitter.on("/").splitToList(url);
        //  System.out.println("list:"+list);
        if (list != null) {
            for (String s : list) {
                if (s.contains("{")) {
                    int indexStart = s.indexOf("{") + 1;
                    int indexEnd = s.indexOf("}");
                    pathList.add(s.substring(indexStart, indexEnd));
                }
            }
            System.out.println("pathList is :" + pathList);
            return pathList;
        }
        return null;
    }


    /**
     * 获取Query List
     *
     * @param url
     * @return 如果没有参数返回 null
     */
    public List<String> getQueryList(String url) {
        if (url.contains("?")) {//如果不判断，结果将返回原来的url
            String substring = url.substring(url.indexOf('?') + 1);
            //如果没有&，也就是只有一个参数的情况，也是不会出问题的
            List<String> list = Splitter.on("&").splitToList(substring);
            List<String> queryList = new ArrayList<>();
            if (list != null) {
                for (String s : list) {
                    List<String> split = Splitter.on("=").splitToList(s);
                    queryList.add(split.get(0));
                }
            }
            System.out.println("queryList is " + queryList);
            return queryList;
        }

        return null;
    }

    public String getPathString(String url) {
        List<String> list = Splitter.on("?").splitToList(url);
        String str = list.get(0);
        System.out.println("str=" + str);
        String substring = str.substring(str.indexOf("."));//去除了https://
        System.out.println("substring=" + substring);
        String pathString = substring.substring(substring.indexOf("/"));

        System.out.println("pathString=" + pathString);
        return pathString;
    }


    private void createRetrofitService(String className, String url) throws Exception {

        file = new File("retrofit-tool/src/main/java/com/xzh/service/" + className + ".java");
        //  file.deleteOnExit();
        StringBuilder topBuilder = new StringBuilder();
        String pakageName = "com.xzh.service";
        topBuilder.append("package " + pakageName + ";\n\n");
        appendImport(topBuilder);
        topBuilder.append("public interface " + className + " {\n\n");

        Files.write(topBuilder,file,Charsets.UTF_8);

        mainBuilder(url);

    }

    private StringBuilder mainBuilder(String url) throws IOException {
        StringBuilder mainBuilder=new StringBuilder();

        //TODO 这里加上请求方式和后半段的restful path片段就行
        mainBuilder.append("\t@" + requestType + "(\"" + getPathString(url) + "\")\n");
        List<String> pathList = getPathByRestful(url);
        List<String> queryList = getQueryList(url);

        mainBuilder.append("\tObservable<String> " + getFuncName(url) + "(");
        //添加pathList参数
        if (pathList != null) {
            for (int i = 0; i < pathList.size(); i++) {
                if (i == 0) {
                    mainBuilder.append("@Path(\"" + pathList.get(i) + "\") " + getParamType(pathList.get(i)) + " " + pathList.get(i));
                } else {
                    mainBuilder.append(", @Path(\"" + pathList.get(i) + "\") " + getParamType(pathList.get(i)) + " " + pathList.get(i));
                }
            }
        }
        //添加queryList参数
        if (queryList != null) {
            for (int i = 0; i < queryList.size(); i++) {
                mainBuilder.append(", @Query(\"" + queryList.get(i) + "\") " + getParamType(queryList.get(i)) + " " + queryList.get(i));
            }
        }
        mainBuilder.append(");\n\n");
        Files.append(mainBuilder, file, Charsets.UTF_8);


      //  Files.append(mainBuilder,file,Charsets.UTF_8);
        return mainBuilder;
    }

    /**
     * 检测测试类型 如果包含size,length,id则为int。money,amount为double。其它为String
     *
     * @param param
     * @return String
     */
    private String getParamType(String param) {
        System.out.println("param is=" + param);
        String s = param.toLowerCase();
        if (!Strings.isNullOrEmpty(s)) {
            if (s.contains("size") || s.contains("length") || s.contains("id")) {
                return "int";
            } else if (s.contains("money") || s.contains("amount")) {
                return "double";
            }
        }
        return "String";
    }

    /**
     * 生成每个请求的方法名
     *
     * @return
     */
    public String getFuncName(String url) {
        String pathString = getPathString(url);
        String funcNmae = "";
        if (url != null) {
            if (pathString.lastIndexOf("}") == pathString.length() - 1) {
                int i = pathString.lastIndexOf("{");
                funcNmae = pathString.substring(i + 1).replace("}", "");
            } else {
                int i = pathString.lastIndexOf("/");
                funcNmae = pathString.substring(i + 1);

            }
        }
        return funcNmae;
    }


    /**
     * TODO 计算参数总个数，方便添加逗号
     *
     * @return
     */
    private int calArgSize() {
        return 0;
    }

    /**
     * 其实可能用不到那么多Query,直接封装到一个Body里面就行，但造成的问题就是请求类越来约多而且很可能重复
     * TODO 还是写Query比较好，这样就不需要请求类了
     * 如果Query非常多，那么写Body类也是麻烦的，还不在地址把所有条件写上。不过一般情况也不会太多，5个最多了
     */
    /*hide*/
    public void createBody() {

        return;
    }

    /**
     * @param builder
     */
    private void appendImport(StringBuilder builder) {
        //basic import
        builder.append("import java.util.List;\n");
        //rxjava import
        builder.append("import io.reactivex.Observable;\n");

        //retrofit import
        builder.append("import retrofit2.http.GET;\n");
        builder.append("import retrofit2.http.POST;\n");
        builder.append("import retrofit2.http.Path;\n");
        builder.append("import retrofit2.http.Query;\n");
        builder.append("import retrofit2.http.QueryMap;\n");
        builder.append("import retrofit2.http.QueryName;\n");
        builder.append("import retrofit2.http.Body;\n");
        builder.append("import retrofit2.http.Field;\n");
        builder.append("import retrofit2.http.FieldMap;\n");
        builder.append("import retrofit2.http.FormUrlEncoded;\n");
        builder.append("import retrofit2.http.Header;\n");
        builder.append("import retrofit2.http.HEAD;\n");
        builder.append("import retrofit2.http.HTTP;\n");
        builder.append("import retrofit2.http.Multipart;\n");
        builder.append("import retrofit2.http.OPTIONS;\n");
        builder.append("import retrofit2.http.PUT;\n");
        builder.append("import retrofit2.http.PATCH;\n");
        builder.append("import retrofit2.http.Streaming;\n");
        builder.append("\n");
    }


    public static void main(String[] args) {
        RetrofitTool tool = new RetrofitTool();

//        RetrofitTool tool2 = new RetrofitTool();
//        tool2.getPathByRestful(getUrl2);
//        tool2.getQueryList(getUrl2);
//
//        RetrofitTool tool3 = new RetrofitTool();
//        tool2.getPathByRestful(getUrl3);
//        tool2.getQueryList(getUrl3);
        try {
            tool.createRetrofitService("RepoService", getUrl);

             tool.mainBuilder(getUrl2);
            StringBuilder endBuilder=new StringBuilder();
            endBuilder.append("}");
           Files.append(endBuilder,file,Charsets.UTF_8);
            // tool2.createRetrofitService("RepoService", getUrl2);
           // tool3.createRetrofitService("RepoService3", getUrl3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
