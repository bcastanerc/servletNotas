<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="icon"
      href="https://w7.pngwing.com/pngs/263/431/png-transparent-javaserver-pages-jar-java-servlet-computer-software-jar-text-logo-computer-programming-thumbnail.png"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous"
    />
    <title>Create notes</title>
  </head>
  <body>
  <header>
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark scrolling-navbar">
      <div class="collapse navbar-collapse" >
        <ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="/createNotes">Create Notes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/userNotes">Your Notes</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/userInfo">Profile</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-danger" href="/login">Log-Out</a>
          </li>
        </ul>
        <div class="d-flex flex-row">
          <div class="form-inline mr-auto">
            <input class="form-control" type="text" placeholder="Search" aria-label="Search">
          </div>
          <div class="form-inline mr-auto my-0 ml-sm-2 ">
            <select id="inputState" class="form-control">
              <option name="titleSearch" selected>By title</option>
              <option name="textSearch">By text</option>
              <option name="expresionSearch">By expresion</option>
              <option name="creationDateSearch">By creation date</option>
              <option name="modificationDateSearch">By modification date</option>
            </select>
          </div>
          <button href="#!" class="btn btn-outline-blue btn-md my-0 ml-sm-2 btn-light" type="submit">Search</button>
        </div>
      </div>
    </nav>
  </header>
  <br><br>
    <h1 class="display-2 d-flex justify-content-center">Your Notes</h1>
    <main class="container">
      <table class="table table-striped">
        <tr>
          <th>id</th>
          <th>title</th>
          <th>text</th>
          <th>creation_date</th>
          <th>last_modification</th>
          <th>user_id</th>
        </tr>
        <c:forEach var="n" items="${notes}">
          <tr>
              <td>${n.id}</td>
              <td>${n.title}</td>
              <td>${n.text}</td>
              <td>${n.creation_date}</td>
              <td>${n.last_modification}</td>
              <td>${n.user_id}</td>
          </tr>
        </c:forEach>
      </table>
    </main>
    <!-- Boostrap script-->
    <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
