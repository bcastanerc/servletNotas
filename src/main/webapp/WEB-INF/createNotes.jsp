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
            <a class="nav-link text-danger" href="?logout">Log-Out</a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <br><br>
    <h1 class="display-2 d-flex justify-content-center">Create Notes</h1>
    <main class="container">
        <form method="POST" action="/createNotes">
            <div class="form-group">
                <label>Title of the note</label>
                <input type="text" class="form-control" name="title" aria-describedby="basic-addon1">
            </div>

            <div class="form-group">
                <label>Text of the note</label>
                <textarea class="form-control rounded-0" name="text" rows="20"></textarea>
                <style>textarea {resize: none; overflow: auto;}</style>
            </div>

            <button type="submit" class="btn btn-primary">Create note</button>
        </form>
    </main>
    <!-- Boostrap script-->
    <script
      src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
      integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
