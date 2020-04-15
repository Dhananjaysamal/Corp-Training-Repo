<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Account Registration</title>

<style>
  .error {
      color: #EF1313;
      font-style: italic;
  }
  .errorblock {
         color: #000;
         background-color: #ffEEEE;
         border: 3px solid #ff0000;
         padding: 8px;
         margin: 16px;
      }
</style>
</head>
<body>
    <div align="center">
        <h1>New/Edit Account Holder</h1>
        <form:form action="saveUserDetails" method="post" modelAttribute="userRegistrationBean">
        <form:errors path = "*" cssClass = "errorblock" element = "div" />
        <table>
            <form:hidden path="userId"/>
            <tr>
                <td>User First Name:</td>
                <td><form:input path="firstName" /><form:errors path="firstName" cssClass = "error"></form:errors>
                </td>
            </tr>
            <tr>
                <td>User Last Name:</td>
                <td><form:input path="lastName" /><form:errors path="lastName" cssClass = "error"></form:errors></td>
            </tr>
            <tr>
                <td>User Address:</td>
                <td><form:input path="userAddress" /><form:errors path="userAddress" cssClass = "error"></form:errors></td>
            </tr>
            <tr>
                <td>Email :</td>
                <td><form:input path="email" /><form:errors path="email" cssClass = "error"></form:errors></td>
            </tr>
            <tr>
                <td>Mobile Number :</td>
                <td><form:input path="mobileNumber" /><form:errors path="mobileNumber" cssClass = "error"></form:errors></td>
            </tr>
             <tr>
                <td>AccountType :</td>
                <td><form:select path="accountType">
                 <option value= "select">select</option>
                <option value="Saving Account">Saving Account</option>
                <option value="Current Account">Current Account</option>
                </form:select>
                <form:errors path="accountType" cssClass = "error"></form:errors></td>
            </tr>
            
             <tr>
                <td>Deposite Amount :</td>
                <td><form:input path="accountBalance" /><form:errors path="accountBalance" cssClass = "error"></form:errors></td>
            </tr>
            
             <tr>
                <td>Password :</td>
                <td><form:input path="password" /><form:errors path="password" cssClass = "error"></form:errors></td>
            </tr>
            
             <tr>
                <td>Terms And Conditions :</td>
                <td><form:checkbox path="accountEnabled" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Register"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>