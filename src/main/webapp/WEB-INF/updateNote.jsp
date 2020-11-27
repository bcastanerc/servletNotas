<!DOCTYPE html>
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
    <title>Update note</title>
  </head>
  <body>
  <jsp:directive.include file="./nav.jsp" />
    <h1 class="display-2 d-flex justify-content-center">Update Notes</h1>
    <main class="container">
      <form method="POST" action="/updateNote">
          <div class="form-group">
              <label>Title of the note</label>
              <input id="title" type="text" class="form-control" name="title" aria-describedby="basic-addon1" value = "${title}">
          </div>
          <div class="form-group">
              <label>Text of the note</label>
              <textarea class="form-control rounded-0" name="text" rows="20">${text}</textarea>
              <style>textarea {resize: none; overflow: auto;}</style>
          </div>
          <button type="submit" class="btn btn-primary">submit edit</button>
          <a href="/updateNote?id=${id}&deleteNote" class="btn btn-danger">Delete note</a>
          <input type="hidden" name="id" value="${id}">
          <input type="hidden" name="_csrftoken" value="${csrfToken}">
      </form>
      <form method="POST" action="/updateNote">
        <div class="form-group">
          <label style="margin-top: 10px;">Share This Note</label>
          <input
            type="email"
            class="form-control"
            aria-describedby="emailHelp"
            placeholder="Enter email"
            name="emailToShare"
          />
          <small class="form-text text-muted"
            >Introduce the email of the person to share</small
          >
        </div>
        <button type="submit" class="btn btn-primary">Share</button>
        <input type="hidden" name="id" value="${id}">
        <input type="hidden" name="_csrftoken" value="${csrfToken}">
      </form>
      <c:if test="${error eq true}">
        <div style="margin-top: 20px;" class="alert alert-warning fade show">
          <strong>Warning!</strong> You can't edit a note if you are not the owner.
          <button type="button" class="close" data-dismiss="alert"></button>
        </div>
      </c:if>
    </main>
    <!-- Boostrap script-->
    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
