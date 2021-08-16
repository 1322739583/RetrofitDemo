package com.xzh.retrofit_tool;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class RetrofitTool {
    private static String url="http://www.githun.com/repo/{user}/{id}";
    private static String url2="http://www.githun.com/repos/{user}/{repo}/releases/{id}/assets";

    private String requestType="GET";
    public RetrofitTool(String url) {
      this.url=url;
    }

    /**
     * 判断是不是path是没有什么意义的，这个在文档里面就应该写上的，是restful的规范
     * @Deprecated 用getPathByRestful
     * @param url
     * @return
     */
    @Deprecated
    public List<String> getPath(String url){
        //这样写比较保险，可以防止http://www.xxx.com/user/add.action?id=xxx这种情况下出现错误
        int index = url.indexOf('.');//先把开头的http://www去掉
        String substring = url.substring(index);
        int index2 = substring.indexOf("/");

        //去除第一个'/'
        String path=substring.substring(index2+1);
        List<String> pathList = Splitter.on("/").splitToList(path);
        System.out.println("the list is:"+pathList.toString());
        //获取最后一个元素
        System.out.println(pathList.get(pathList.size()-1));

        return pathList;
    }

    /**
     * 根据符合RestFul的API获取path
     * @param url
     * @return
     */
    public List<String> getPathByRestful(String url){

        List<String> pathList=new ArrayList<>();
        List<String> list = Splitter.on("/").splitToList(url);
        System.out.println("list:"+list);
        for (String s : list) {
            if (s.contains("{")){
                int indexStart=s.indexOf("{")+1;
                int indexEnd=s.indexOf("}");
                pathList.add(s.substring(indexStart,indexEnd));
            }
        }
        return pathList;
    }


    /**
     * 获取Query List
     * @param url
     * @return 如果没有参数返回 null
     */
    public List<String> getQuery(String url){
        if (url.contains("?")) {//如果不判断，结果将返回原来的url
            String substring = url.substring(url.indexOf('?') + 1);
            //如果没有&，也就是只有一个参数的情况，也是不会出问题的
            List<String> list = Splitter.on("&").splitToList(substring);
            List<String> queryList = new ArrayList<>();
            for (String s : list) {
                List<String> split = Splitter.on("=").splitToList(s);
                queryList.add(split.get(0));
            }
            System.out.println("queryList is " + queryList);
            return queryList;
        }

        return null;
    }

    /**
     *
     * @throws Exception
     */
    private void createRetrofitService( )throws Exception{
       //像这样中间做path的应该是非常少的
      /*  @GET("/users/{user}/repos")
        Observable<List<Repo>> getRepos(@Path("user") String user);*/

       //可以将最后的path识别为id
       //https://www.pixiv.net/artworks/92028009
        // https://www.weibo.com/dapengpeng?refer_flag=1001205010_&is_all=1

        File file = new File("retrofit-tool/src/main/java/com/xzh/service/RepoService.java");
        //  file.deleteOnExit();
        StringBuilder builder=new StringBuilder();
        String pakageName="com.xzh.service";
        String className="RepoService";
        builder.append("package "+pakageName+";\n");
        appendImport(builder);
        builder.append("public interface "+className+" {\n\n");
        //@TODO 可以通过让用户mark是不是path来获取,或者先不要实现path的功能，只实现query的功能
        //@TODO 可以通过判断是否是数字来确定是不是path
        builder.append("\t@"+requestType+"(\"/users/{user}/repos\")\n");
        builder.append("\tObservable<List<String>> getRepos(@Path(\"user\") String user);\n");
        builder.append("\n}");
        Files.write(builder, file, Charsets.UTF_8);
    }

    private void createRetrofitService2( )throws Exception{

      /*  @GET("/users/{user}/repos")
        Observable<List<Repo>> getRepos(@Path("user") String user);*/

        File file = new File("retrofit-tool/src/main/java/com/xzh/service/RepoService2.java");
        //  file.deleteOnExit();
        StringBuilder builder=new StringBuilder();
        String pakageName="com.xzh.service";
        String className="RepoService2";
        builder.append("package "+pakageName+";\n");
        appendImport(builder);
        builder.append("public interface "+className+" {\n\n");
        builder.append("\t@POST(\"/repos/{user}/{repo}/releases/{id}/assets\")\n");
        List<String> pathList=getPathByRestful(url2);
        List<String> query = getQuery(url);
        builder.append("\tObservable<String> uploadAssert(");
        for (int i = 0; i <pathList.size() ; i++) {
            builder.append("@Path(\""+pathList.get(i)+"\") String "+pathList.get(i));
            //TODO 这样写是不对的，还需要算Query等list的大小
            if (i!=pathList.size()-1){
                //最后一个不加，
                builder.append(", ");
            }
        }


        builder.append(");\n");
        builder.append("\n}");
        Files.write(builder, file, Charsets.UTF_8);
    }

    /**
     *  其实可能用不到那么多Query,直接封装到一个Body里面就行，但造成的问题就是请求类越来约多而且很可能重复
     *   TODO 还是写Query比较好，这样就不需要请求类了
     *   如果Query非常多，那么写Body类也是麻烦的，还不在地址把所有条件写上。不过一般情况也不会太多，5个最多了
     */
    /*hide*/
    public void createBody(){

    }

    /**
     *
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
    }


    public static void main(String[] args) {

        System.out.println("hello");
        RetrofitTool tool=new RetrofitTool(url);
       // tool.getPath(url);
        tool.getPathByRestful(url2);
        tool.getQuery(url);
        RetrofitTool tool2=new RetrofitTool(url2);
        tool.getPath(url2);
        try {
            tool.createRetrofitService();
            tool.createRetrofitService2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
