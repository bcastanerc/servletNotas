<!DOCTYPE html>
<jsp:useBean id="sanitize" class="com.liceu.notes.utils.sanitize"/>
<jsp:useBean id="markdown" class="com.liceu.notes.utils.markdown"/>
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
    <title>View note</title>
  </head>
  <body>
  <jsp:directive.include file="./nav.jsp" />
    <main style class="container">
      <h2 style="margin-top: 40px;">${sanitize.sanitizeNote(note.title)}</h2>
      <div style="margin-top: 40px; margin-bottom: 40px;">
        ${markdown.renderNote(note.text)}
      </div>
      <div class="mb-2">
        <small class="text-muted"><b>Owner:</b> ${ownerEmail}</small>
        <small class="text-muted"><b>Creation:</b> ${note.creation_date}, <b>Last Edit:</b> ${note.last_modification}</small>
      </div>
      <c:if test="${owner eq true}">
        <a href="/updateNote?id=${note.id}" class="btn btn-primary">Edit</a>
      </c:if>
    </main>
    <!-- Boostrap script-->
    <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
