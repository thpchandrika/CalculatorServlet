package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/calc")
public class CalculatorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show calculator page
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //show result page
        PrintWriter out = response.getWriter();
        double number1 = Double.parseDouble(request.getParameter("number1"));
        double number2 = Double.parseDouble(request.getParameter("number2"));
        String operation = request.getParameter("op");
        double result = 0;
        try {
            result = doCalculation(number1, number2,operation);
        } catch (Exception e) {
            out.println(e.getMessage());
            return;
        }

        HttpSession session = request.getSession();
        List<Calculation> previousCalculations = (List<Calculation>) session.getAttribute("previousCalculations");
        if (previousCalculations == null) {
            previousCalculations = new ArrayList<>();
        }
        previousCalculations.add(new Calculation(number1, number2,operation,result));
        session.setAttribute("previousCalculations", previousCalculations);

        out.println("<html>");
        out.println("<head>SIMPLE CALCULATOR<br><br><br></head>");
        out.println("<body>");
        out.println("<form method = 'post' action = 'calc'>");
        out.println("enter the first number:<br>");
        out.println("<input type = 'text' name='number1'><br><br>");
        out.println("enter the second number:<br>");
        out.println("<input type = 'text' name='number2'><br><br>");
        out.println("enter the operation:<br><br>");
        out.println("<input type ='radio' name = 'op' value = '+'>add<br>");
        out.println("<input type = 'radio' name = 'op' value = '-'>sub<br>");
        out.println("<input type = 'radio' name = 'op' value = '*'>mul<br>");
        out.println("<input type = 'radio' name = 'op' value = '/'>div<br><br>");
        out.println("<input type = 'submit' name = 'result' value = 'submit'><br>");
        System.out.println(result);
        out.println("<h3>The result of " + number1 + operation + number2 + "=" + result + "</h3>");
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("<th>first</th><th>operation</th><th>second</th><th>result</th></thead>");
        out.println("<tbody>");
        for (Calculation calculation : previousCalculations){
            out.println("<tr><td>" +
                    calculation.getFirst() + "</td><td>" +
                    calculation.getOperation() + "</td><td>" +
                    calculation.getSecond() + "</td><td>" +
                    calculation.getResult() + "</td></tr>");
        }
        out.println("</tbody></table>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }

    private static double doCalculation(double number1, double number2, String operation)
            throws Exception {
       double result =0;
        switch (operation){
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case  "/":
                if (number2 != 0) {
                    result = number1 / number2;
                } else {
                    throw new ArithmeticException("Divide by zero exception");
                }
                break;
            default:
                throw new ArithmeticException("Invalid operation");
        }
        return  result;
    }

}
