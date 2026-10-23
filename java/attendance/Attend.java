package attendance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Attend")
public class Attend extends HttpServlet {
    private static final Map<String, String> sid2seat = new HashMap<>();

    private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Simple Attendance by Seat Id<br><br>");

        String studentId = request.getParameter("studentId");
        String seatId = request.getParameter("seatId");

        if (studentId == null) {
            // 查看列表
            if (sid2seat.isEmpty()) {
                out.println("No sign-in record yet<br><br>");
            } else {
                for (Map.Entry<String, String> entry : sid2seat.entrySet()) {
                    out.println(entry.getKey() + " : " + entry.getValue() + "<br>");
                }
            }
            out.println("<br><a href=' '>Attendance Submission</a >");
        } else {
            if (studentId.isBlank()) {
                // 学号为空
                out.println("Student Id can not be blank<br><br>");
                out.println("<a href='index.html'>Attendance Submission</a > | "
                        + "<a href='Attend'>Attendee List</a >");
            } else {
                // 正常签到存入
                sid2seat.put(studentId, seatId);
                out.println(studentId + ":" + seatId + " is recorded<br><br>");
                out.println("<a href='index.html'>Attendance Submission</a > | "
                        + "<a href='Attend'>Attendee List</a >");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }
}