package servlet;

import java.io.IOException;
import java.util.List;

import impl.AirplaneDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pojo.Airplane;

//用于后台飞机管理
@WebServlet("/Airplane")
public class AirplaneServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String method=req.getParameter("method");
		System.out.println(method);
		switch(method) {
			case "listAirplane":
				listAirplane(req, res);
				break;
			case "addAirplane":
				addAirplane(req, res);
				break;
			case "queryAirplane":
				queryAirplane(req, res);
				break;
			case "updateAirplane":
				updateAirplane(req, res);
				break;
			case "submitUpdate":
				submitUpdate(req, res);
				break;
			case "deleteAirplane":
				deleteAirplane(req, res);
				break;
			default:
				break;
		}
	}
		
	//添加航班
	public void addAirplane(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AirplaneDAOImpl airplaneImpl =new AirplaneDAOImpl();
		System.out.println("here");
		String pno=req.getParameter("pno");
		String cname=req.getParameter("cname");
		String ptype=req.getParameter("ptype");
		String capcity=req.getParameter("capcity");
		String[]info=new String[4];
		info[0]=pno;
		info[1]=cname;
		info[2]=ptype;
		info[3]=capcity;
		airplaneImpl.addAirplane(info);
//		req.getRequestDispatcher("ManageAirplane.jsp").forward(req, res);
		req.setAttribute("pageNo", 1);
		listAirplane(req, res);
	}
	
	//删除航班
	public void deleteAirplane(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pno=req.getParameter("pno");
		System.out.println(pno);
		AirplaneDAOImpl airplaneImpl =new AirplaneDAOImpl();
		airplaneImpl.deleteAirplane(pno);
		req.setAttribute("pageNo", 1);
		listAirplane(req, res);
	}
	
	//更新航班
	public void submitUpdate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AirplaneDAOImpl airplaneImpl =new AirplaneDAOImpl();
		System.out.println("here");
		String pno=req.getParameter("pno");
		String cname=req.getParameter("cname");
		String capcity=req.getParameter("capcity");
		String ptype=req.getParameter("ptype");
		String[]info=new String[4];
		info[0]=cname;
		info[2]=capcity;
		info[1]=ptype;
		info[3]=pno;
		airplaneImpl.updateAirplane(info);
		req.setAttribute("pageNo", 1);
		listAirplane(req, res);
	}
	
	//通过id找航班并初始化到update界面
	public void updateAirplane(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String pno=req.getParameter("pno");
		AirplaneDAOImpl airplaneImpl =new AirplaneDAOImpl();
		String sql="select * from \"t_airplane\" where \"pno\"="+"'"+pno+"'";
		List<Airplane> airplaneList=airplaneImpl.queryAirplane(sql);
		req.setAttribute("airplane", airplaneList.get(0));
		System.out.println(airplaneList.get(0).getPno());
		req.getRequestDispatcher("UpdateAirplane.jsp").forward(req, res);
	}
	
	//展示航班
	public void listAirplane(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//获取当前页码
		String pageNostring=req.getParameter("pageNo");
		int pageNo=1;
		if (pageNostring!=null) {
			pageNo=Integer.parseInt(pageNostring);
		}
		
		System.out.println(pageNo);
		int pageSize=5;
		AirplaneDAOImpl airplaneImpl =new AirplaneDAOImpl();
		List<Airplane> allairplane=airplaneImpl.showAirplane();
		//总记录数
		int totalCount=allairplane.size();
		//总页数
		int totalPage=totalCount/pageSize;
		if(totalCount%pageSize!=0||totalPage==0) {
			totalPage++;
		}
		//下一页
		int nextPage=pageNo;
		if(nextPage<totalPage) {
			nextPage=pageNo+1;
		}
		//上一页
		int prePage=pageNo;
		if(prePage>1) {
			prePage=pageNo-1;
		}
		List<Airplane> airplaneList=airplaneImpl.listAirplane(pageNo, pageSize);
		req.setAttribute("airplaneList", airplaneList);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nextPage", nextPage);
		req.setAttribute("prePage", prePage);
		req.setAttribute("pageNo", pageNo);
		req.setAttribute("pageSize", pageSize);
		req.getRequestDispatcher("ManageAirplane.jsp").forward(req, res);
	}
	
	//条件查询航班
	public void queryAirplane(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		AirplaneDAOImpl airplaneImpl =new AirplaneDAOImpl();
		String pno=req.getParameter("pno");
		String cname=req.getParameter("cname");
		String ptype=req.getParameter("ptype");
		String sql="select * from \"t_airplane\"";
		if(!pno.equals("")||!cname.equals("")||!ptype.equals("")) {
			sql+=" where";
			int flag=0;
			if(!pno.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"pno\"="+"'"+pno+"'";
				}else {
					sql+=" and \"pno\"="+"'"+pno+"'";
				}
				
			}
			if(!cname.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"cname\"="+"'"+cname+"'";
				}else {
					sql+=" and \"cname\"="+"'"+cname+"'";
				}
			}
			if(!ptype.equals("")) {
				if(flag==0) {
					flag++;
					sql+=" \"ptype\"="+"'"+ptype+"'";
				}else {
					sql+=" and \"ptype\"="+"'"+ptype+"'";
				}
			}
		}
		List<Airplane> airplaneList=airplaneImpl.queryAirplane(sql);
		req.setAttribute("airplaneList", airplaneList);
		req.getRequestDispatcher("ManageAirplane.jsp").forward(req, res);
	}
}
