<%-- 
    Document   : bookingFail
    Created on : 20.05.2017, 17:32:25
    Author     : Mia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ui:composition template="../WEB-INF/template.xhtml">

             <ui:define name="content">
                 <h3>
                     Sorry, an error occured in the booking process!
                     Please try again!
                 </h3>
                 
                     <h:form>
                         
                         <h:commandButton action="#{hotelModel.startBookingProcess()}" value="ok"/>
                    </h:form>
                
                 
             </ui:define>
         </ui:composition>
    </body>
</html>
