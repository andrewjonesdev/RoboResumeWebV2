package backEnd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import haveYouSeenRoboCup.Skill;

/**
 * Servlet implementation class SkillS
 */
@WebServlet("/Skill")
public class SkillS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList <Skill> skillList = new ArrayList();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String addSkill = "";
		String errorSkill = "";
		String entrySkill = "";
		try{
			if(!request.getParameter("skillName").isEmpty()&&!request.getParameter("rating").isEmpty()){
				Skill skill = new Skill(request.getParameter("skillName"), request.getParameter("rating"), ResumeS.getResume());
				skillList.add(skill);
				addSkill = "Added";
				request.setAttribute("addSkill", addSkill);
			}
			else{
				errorSkill = "Error:&nbsp;Invalid&nbsp;Skill";
				request.setAttribute("errorSkill", errorSkill);
			}
		}
		catch(Exception e){
			errorSkill = "Error:&nbsp;Invalid&nbsp;Skill";
			request.setAttribute("errorSkill", errorSkill);
		}
		finally{
			if(skillList.size()==1){
				entrySkill = skillList.size() + "&nbsp;Entry";
			}
			else{
				entrySkill = skillList.size() + "&nbsp;Entries";
			}
		request.setAttribute("entrySkill", entrySkill);
		String nextURL = "/ResumeHome.jsp";
		getServletContext().getRequestDispatcher(nextURL).forward(request,response);
		}
		//doGet(request, response);
	}
	public static ArrayList <Skill> getSkillList(){
		return skillList;
	}
}
