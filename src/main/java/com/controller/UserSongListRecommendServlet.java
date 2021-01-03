package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.entity.SongList;
import com.entity.User;
import com.service.impl.SongListServiceImpl;
import com.service.SongListServiceInter;
import com.service.impl.UserWithSongListServiceImpl;
import com.service.UserWithSongListServiceInter;

/*
 * 用户推荐逻辑代码
 */
public class UserSongListRecommendServlet extends HttpServlet {
	private SongListServiceInter songListService = new SongListServiceImpl();
	private ArrayList<SongList> songListArrayList = null;//存储查询到的歌单对象
	private int songListArrayListSize = 0;//存储歌单对象的集合的长度
	private Map<String, Integer> songListTagsNameMap = new HashMap<String, Integer>();//存储歌单标签名字和出现次数
	private java.util.List<Map.Entry<String, Integer>> songListTagsNameListClassement = new ArrayList<Map.Entry<String,Integer>>();//存储排序后歌单标签名字和出现次数
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	//获取歌单所有标签集合(并排序，形式参数是歌单集合长度和集合)
	public Map<String, Integer> getSongListTagsNameMap (int songListArrSize, ArrayList<SongList> songListArrayList, Map<String, Integer> songListTagsNameMap){
		//传入的参数是查询到的歌单对象集合的长度
		//返回值是出现的所有标签以及出现次数但未排序的标签集合
		
		System.out.println("进来了吗？获取用户创建歌单的标签：" + songListArrSize);
		for (int i = 0; i < songListArrSize; i++) {
			
			/*
			 * 获取每个歌单的标签值
			 * 每个歌单，可以有多个标签，每个标签之间用空格分隔
			 * 也就是每个歌单的标签值是一个数组
			 */
			
			String songListTagsString = songListArrayList.get(i).getTags();
			//分割字符串为数组
			if (songListTagsString != null && !songListTagsString.equals("")) {
				String songListTagsArr[] = songListTagsString.split(" ");
				System.out.println("-----------------用户每个歌单的标签数组:----------------");
				System.out.println("歌单标签值：" + songListTagsString);
				for(int s = 0; s < songListTagsArr.length; s++){
					System.out.print(songListTagsArr[s] + ",");
				}
				System.out.println("");
				System.out.println("--------------------------------------------------");
				//遍历歌曲标签数组
				int songListTagsArrLength = songListTagsArr.length;
				System.out.println("用户每个歌单标签数组长度：" + songListTagsArrLength);
				for (int j = 0; j < songListTagsArrLength; j++) {
					System.out.println("遍历每个数组：当前数据：" + songListTagsArr[j]);
					System.out.println("集合长度：" + songListTagsNameMap.size());
					//把标签存入songListTagsNameMap集合中
					//首先判断集合中的键是否已经存在该值，如果已经存在就取出该键的值，并+1操作
					//先判断集合是否为空，遍历集合
					if (songListTagsNameMap.size() == 0) {
						System.out.println("集合为空，添加集合，添加数据为：" + songListTagsArr[j]);
						songListTagsNameMap.put(songListTagsArr[j], 1);
					}else{
						boolean theKeyExistOrNot = true;//记录该标签在集合中是否已经存在，如果存在，返回false，不存在true
						String songListTagsName = null;
						Integer songListTagsNameCount = 0;
						for (Map.Entry<String, Integer> songListTagsNameMapEntry : songListTagsNameMap.entrySet()) {
							
							//初步判断，这里集合一开始为空，没有遍历，所依，没有添加进去
							//获取键（标签名）
							songListTagsName = songListTagsNameMapEntry.getKey();
							//获取值（出现次数）
							songListTagsNameCount = songListTagsNameMapEntry.getValue();
							System.out.println("集合不为空，集合已存在数据：" + songListTagsName + ":" + songListTagsNameCount);
							for (Map.Entry<String, Integer> songListTagsNameMapEntryExist : songListTagsNameMap.entrySet()) {
								//遍历集合，查找数据是否已经存在
								songListTagsName = songListTagsNameMapEntryExist.getKey();
								if (songListTagsName.equals(songListTagsArr[j])) {
									//表明该标签在集合中已经存在，修改theKeyExistOrNot为false
									songListTagsNameCount = songListTagsNameMapEntryExist.getValue();
									theKeyExistOrNot = false;
									break;
								}else {
									//表示该标签在集合中并不存在,修改theKeyExistOrNot为true
									theKeyExistOrNot = true;
								}
							}
							
						}
						if (theKeyExistOrNot) {
							//如果theKeyExistOrNot为true说明该标签在集合中还不存在，直接添加
							//直接放入集合，并把值设置为1
							System.out.println("标签不存在，集合不为空，添加集合，添加数据为：" + songListTagsArr[j]);
							songListTagsNameMap.put(songListTagsArr[j], 1);
						}else{
							//该标签在集合中已经存在
							//把该标签的值改为原来的值+1
							//直接用已经存在的键放入，会把原来的覆盖
							System.out.println("标签存在，集合不为空，添加集合，添加数据为：" + songListTagsArr[j] + "值为：" + songListTagsNameCount);
							songListTagsNameMap.put(songListTagsName, songListTagsNameCount+1);
						}
					}
					
				}
			}
		}
		//输出集合中存在的标签
		System.out.println("未排序输出所有值：");
		for (Map.Entry<String, Integer> sss : songListTagsNameMap.entrySet()) {
			System.out.println("key: " + sss.getKey() + ";value: " + sss.getValue());
		}
		return songListTagsNameMap;
	}
	//为获取到的标签集合排序
	public ArrayList<Map.Entry<String, Integer>> songListTagsNameComm(Map<String, Integer> songListTagNaMap){
		//传入参数整理好的歌单中的所有标签，和出现次数
		//返回值是排序完成后的标签集合
		
		
		//把原集合转换为list
		songListTagsNameListClassement = new ArrayList<Map.Entry<String,Integer>>(songListTagNaMap.entrySet());
		//使用list.sort()排序
		Collections.sort(songListTagsNameListClassement, new Comparator<Map.Entry<String, Integer>>(){
//		songListTagsNameListClassement.sort(new Comparator<Map.Entry<String, Integer>>() {

			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		//集合songListTagsNameClassement是排序后的集合
		System.out.println("根据后面值大小排序输出：");
		for (int j = 0; j < songListTagsNameListClassement.size(); j++) {
			//输出排序后集合
			System.out.println(songListTagsNameListClassement.get(j).getKey() + ":" + songListTagsNameListClassement.get(j).getValue());
		}
		return (ArrayList<Entry<String, Integer>>) songListTagsNameListClassement;
	}
	
	public void setValueBegin(){
		songListArrayList = null;//存储歌单对象
		songListArrayListSize = 0;//存储歌单对象集合长度
		songListTagsNameMap = new HashMap<String, Integer>();//存储歌单标签名字和出现次数
		songListTagsNameListClassement = null;//存储歌单标签排序后的次数
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		songListService = new SongListServiceImpl();
		setValueBegin();
		System.out.println("推荐歌单的类，撒大声地是否发生时发生");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("userInfo");
		int userId = user.getUserId();
		/*用户自己创建的歌单的标签*/
		songListArrayList = songListService.selectAllSongList(userId);//存储歌单对象
		if (songListArrayList == null) {
			songListArrayListSize = 0;
		}else {
			songListArrayListSize = songListArrayList.size();
		}
		//把用户创建的歌单标签名字、出现的次数以键值对方式存入Map集合中
//		System.out.println("用户有创建的歌单吗：" + songListArrayListSize);
		if (songListArrayListSize > 0) {
			/*
			 * 查询到信息
			 * 说明用户有自己创建的歌单
			 * 遍历用户创建的歌单
			 */
//			System.out.println("遍历的用户标签：" + songListTagsNameMap.size());
//			if (songListTagsNameMap.size() > 0) {
//				System.out.println("遍历的用户标签：" + songListTagsNameMap);
				/*如果用户创建歌曲标签集合不为空
				 * 计算用户创建的歌曲表单的标签集合songListTagsNameMap
				*把用户创建的歌单的标签集合按照出现次数进行从大到小排序
				*/
				songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize,songListArrayList, songListTagsNameMap);
				//调用集合排序方法
				songListTagsNameComm(songListTagsNameMap);//集合排序，为用户创建的歌单的标签排序
				
//			}else {
//				//用户创建了歌单，但歌单标签集合为空
//				//其他计算方式推荐
//				//按照歌单热度查询，条件为公开的歌单
//				songListArrayList = songListService.selectSongListOfAccessCount(0);
//				songListArrayListSize = songListArrayList.size();
//				//调用获取歌单标签集合的方法
//				songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize);
//				//给获取到的歌曲标签排序
//				songListTagsNameComm(songListTagsNameMap);
//			}
		}else {
			//用户没有创建任何歌单
			//其他计算方式推荐
			//按照歌单热度查询
			songListArrayList = songListService.selectSongListOfAccessCount(0);
			songListArrayListSize = songListArrayList.size();
			//调用获取歌单标签集合的方法
			songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize,songListArrayList, songListTagsNameMap);
			//给获取到的歌曲标签排序
			songListTagsNameComm(songListTagsNameMap);
		}
//这是用户创建的歌单的标签集合
		//把用户创建的歌单的标签存储起来========权重2
		
		/*计算方式：
		 * 标签出现的次数   * 权重
		 */
		ArrayList<Map.Entry<String, Integer>> songListTagsNameOfUserFonder = (ArrayList<Entry<String, Integer>>) songListTagsNameListClassement;
		
		/*上面是为用户自己创建的歌单所使用的标签进行排序，查询完成后，要把最后集合存储到一个变量中，防止后面的代码将其覆盖*/
		
		/*查询排序 用户  收藏  的歌单的标签*/
		UserWithSongListServiceInter userWithSongListService = new UserWithSongListServiceImpl();
		setValueBegin();//设置初始值
		if (songListTagsNameOfUserFonder == null) {
			songListArrayListSize = 0;
		}else {
			songListArrayList = userWithSongListService.selectSongListOfUserWithSongList(userId);
			songListArrayListSize = songListArrayList.size();
		}
		System.out.println("===============开始值==========：" + songListArrayListSize);
		if (songListArrayListSize > 0) {
			/*如果用户收藏歌曲标签集合不为空
			 * 计算用户收藏的歌曲表单的标签集合songListTagsNameMap
			*把用户收藏的歌单的标签集合按照出现次数进行从大到小排序
			*/
			songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize,songListArrayList,songListTagsNameMap);
			//调用集合排序方法
			songListTagsNameComm(songListTagsNameMap);//集合排序，为用户创建的歌单的标签排序
		}else {
			//用户没有收藏任何歌单
			//其他计算方式推荐
			//按照歌单热度查询
			songListArrayList = songListService.selectSongListOfAccessCount(0);
			songListArrayListSize = songListArrayList.size();
			//调用获取歌单标签集合的方法
			songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize,songListArrayList,songListTagsNameMap);
			//给获取到的歌曲标签排序
			songListTagsNameComm(songListTagsNameMap);
		}
//这是用户收藏的歌单的标签集合
		//存储用户收藏歌单的标签集合====权值3
		/*
		 * 计算方式
		 * 标签出现次数  * 权重
		 */
		ArrayList<Map.Entry<String, Integer>> userCollectionSongListTagsName = (ArrayList<Entry<String, Integer>>) songListTagsNameListClassement;
		
		//根据用户的听歌记录来查询对应歌单的标签
		ArrayList<SongList> songListOfUserListenSong = songListService.selectSongListOfListenCount(userId);
		setValueBegin();//设置初始值
		if (songListOfUserListenSong == null) {
			songListArrayListSize = 0;
		}else {
			songListArrayList = songListOfUserListenSong;
			System.out.println("这里什么错：" + songListOfUserListenSong);
			songListArrayListSize = songListArrayList.size();
		}
		if (songListArrayListSize > 0) {
			songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize,songListArrayList,songListTagsNameMap);
			//调用集合排序方法
			songListTagsNameComm(songListTagsNameMap);//集合排序，为用户创建的歌单的标签排序
		}else {
			//用户没有听歌记录
			//其他计算方式推荐
			//按照歌单热度查询
			songListArrayList = songListService.selectSongListOfAccessCount(0);
			songListArrayListSize = songListArrayList.size();
			//调用获取歌单标签集合的方法
			songListTagsNameMap = getSongListTagsNameMap(songListArrayListSize,songListArrayList,songListTagsNameMap);
			//给获取到的歌曲标签排序
			songListTagsNameComm(songListTagsNameMap);
		}
//这是用户听歌记录的歌单的标签集合
		//存储用户听歌记录歌单的标签集合====权值6
		/*
		 * 计算方式
		 * 标签出现次数  * 权重
		 */
		JSONObject songListJsonObject = new JSONObject();
		ArrayList<Map.Entry<String, Integer>> songListOfUserListenSongArrayList = (ArrayList<Entry<String, Integer>>) songListTagsNameListClassement;
		System.out.println("打印用户听歌记录的歌曲标签值：");
		for (int i = 0; i < songListOfUserListenSongArrayList.size(); i++) {
			System.out.print(songListOfUserListenSongArrayList.get(i).getKey() + ":" + songListOfUserListenSongArrayList.get(i).getValue() + ",");
		}
		System.out.println("");
		//首页个性化推荐，根据用户听歌记录的歌单
		//查询有这些标签的歌单
		//遍历用户听歌记录的歌单标签
		JSONArray commentAllSongList = null;//用来存储多个歌单所有信息
		
		
		JSONObject songListAndCreateUser = new JSONObject();
//		JSONObject oneCommSongListInfo = new JSONObject();//用来存储单个信息，包括每个歌单的，歌单表信息
		System.out.println("用户听歌记录长度：" + songListOfUserListenSongArrayList.size());
		for (int i = 0; i < songListOfUserListenSongArrayList.size(); i++) {
			String userListenSongTags = songListOfUserListenSongArrayList.get(i).getKey();//用户听歌标签
			
			//声明根据歌单标签查询歌单的服务
			SongListServiceInter songListService = new SongListServiceImpl();
			//调用根据标签查询歌单的方法
			ArrayList<SongList> songListArrayList = songListService.selectSongListOfTags("%" + userListenSongTags + "%", userId);//userId表示，推荐的歌单不能使自己创建的
			System.out.println("标签名：" + userListenSongTags + "：根据标签查询到的歌单数量：" + songListArrayList.size());
			//根据歌单编号，查询该歌单中存在的歌曲
			for (int j = 0; j < songListArrayList.size(); j++) {
				
				//歌单编号
				int songListId = songListArrayList.get(j).getSongListId();

//				songListJsonObject.put("user", otherCanSeaUserInfo);
				//根据歌单编号查询歌单信息
				SongList songListInfoOfSongListId = songListService.selectSongListOfSongListId(songListId);
				//歌单访问量（前端需要）
				int accessCount = songListInfoOfSongListId.getAccessCount();
				//歌单编号（前端必须）
				int songListIdToHtml = songListInfoOfSongListId.getSongListId();
				//歌单名（前端必须）
				String songListName = songListInfoOfSongListId.getSongListName();
				//歌单封面
				String songListImg = songListInfoOfSongListId.getSongListImg();
				
				JSONObject songListInfoToHtml = new JSONObject();
				songListInfoToHtml.put("accessCount", accessCount);
				songListInfoToHtml.put("songListId", songListIdToHtml);
				songListInfoToHtml.put("songListName", songListName);
				songListInfoToHtml.put("songListImg", songListImg);
				
				songListJsonObject.put("songList", songListInfoToHtml);
				songListAndCreateUser.put(songListId, songListJsonObject);//存储一个歌单的歌单表和创建的用户表
			}
		}
		
		JSONObject loginUserAndCommentSongList = new JSONObject();
		loginUserAndCommentSongList.put("loginUserInfo", user);
		loginUserAndCommentSongList.put("commentSongList", songListAndCreateUser);
		JSONArray loginUserInfoAndCommentJSONArr = JSONArray.fromObject(loginUserAndCommentSongList);
		System.out.println("=========用户信息与推荐信息===========");
		System.out.println(loginUserInfoAndCommentJSONArr);
		out.print(loginUserInfoAndCommentJSONArr);
	}
}
