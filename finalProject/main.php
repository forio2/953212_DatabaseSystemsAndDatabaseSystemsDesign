<?php
    session_start();
    if($_SESSION['Username'] == ""){
        echo "Please Login!";
        exit();
    }
    $link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever");
    $sql = "SELECT * FROM Customers WHERE usernameCustomer = '".$_SESSION['Username']."' ";
    $objQuery = mysqli_query($link, $sql);
?>
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Hello, world!</title>
  </head>
  <body>
    <!-- navbar-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="index.php"><span class="navbar-brand mb-0 h1">Steak Justice</span></a> 
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
        </div>
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <button onclick="window.location.href = 'profile.php'" type="button" class="btn btn-outline-dark btn-sm">My Profile</button>
            <button onclick="window.location.href = 'logout.php'" type="button" class="btn btn-outline-dark btn-sm">Logout</button>
        </li>
      </ul>
    </nav>
    <!-- image and text-->
    <!-- work with style.css(.img-fluid, .logo-wrapper)-->
    <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid">
          <div class="container">
            <h1>STEAK Justice</h1>
            <p>
              Lorem ipsum dolor sit amet, consectetur 
              adipiscing elit,<br> sed do eiusmod tempor 
              incididunt ut labore et dolore magna<br> aliqua.
              Ut enim ad minim veniam, quis nostrud
              exercitation<br> ullamco laboris nisi ut aliquip 
              ex ea commodo consequat. 
            </p>
          </div>
        </div>
      </div>
    </div>  
    </div>
    <br><br>
    <!-- Menu -->
    <!-- chick go to Order-->
    <div class="container">
      <div class="row">
        <!--Pork-->
        <div class="col">
          <form id="pork" action="order.php" method="POST">
            <div class="row" onclick="document.getElementById('pork').submit();" style="cursor:pointer"> <!-- onclick from form's id -->
              <div class="Menu_img" id="imgPorkMenu">
                <div class="row logo-container-div align-items-center justify-content-center">
                  <div class="logo-wrapper col-auto ">
                    <h1 class="nameMenu">Pork</h1>
                    <h5 id="price">200</h5>
                    <input type="hidden" name="orderValue" value="1"> <!--post to sql-->
                    <input type="hidden" name="orderName" value="Pork"> <!--show order's name -->
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
        <!--Chicken-->
        <div class="col">
          <form id="chicken" action="order.php" method="POST">
            <div class="row" onclick="document.getElementById('chicken').submit();" style="cursor:pointer"> <!-- onclick from form's id -->
              <div class="Menu_img" id="imgChickenMenu">
                <div class="row logo-container-div align-items-center justify-content-center">
                  <div class="logo-wrapper col-auto ">
                    <h1 class="nameMenu">Chicken</h1>
                    <h5 id="price">150</h5>
                    <input type="hidden" name="orderValue" value="2"> <!--post to sql-->
                    <input type="hidden" name="orderName" value="Chicken"> <!--show order's name -->
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
        <!--Beef-->
        <div class="col">
          <form id="beef" action="order.php" method="POST">
            <div class="row" onclick="document.getElementById('beef').submit();" style="cursor:pointer"> <!-- onclick from form's id -->
              <div class="Menu_img" id="imgBeefMenu">
                <div class="row logo-container-div align-items-center justify-content-center">
                  <div class="logo-wrapper col-auto ">
                    <h1 class="nameMenu">Beef</h1>
                    <h5 id="price">300</h5>
                    <input type="hidden" name="orderValue" value="3"> <!--post to sql-->
                    <input type="hidden" name="orderName" value="Beef"> <!--show order's name -->
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>

      <br><br>

      <div class="row">
        <!--Fish-->
        <div class="col">
          <form id="fish" action="order.php" method="POST">
            <div class="row" onclick="document.getElementById('fish').submit();" style="cursor:pointer"> <!-- onclick from form's id -->
              <div class="Menu_img" id="imgFishMenu">
                <div class="row logo-container-div align-items-center justify-content-center">
                  <div class="logo-wrapper col-auto ">
                    <h1 class="nameMenu">Fish</h1>
                    <h5 id="price">250</h5>
                    <input type="hidden" name="orderValue" value="4"> <!--post to sql-->
                    <input type="hidden" name="orderName" value="Fish"> <!--show order's name -->
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
        <!--Sheep-->
        <div class="col">
          <form id="sheep" action="order.php" method="POST">
            <div class="row" onclick="document.getElementById('sheep').submit();" style="cursor:pointer"> <!-- onclick from form's id -->
              <div class="Menu_img" id="imgSheepMenu">
                <div class="row logo-container-div align-items-center justify-content-center">
                  <div class="logo-wrapper col-auto ">
                    <h1 class="nameMenu">Sheep</h1>
                    <h5 id="price">300</h5>
                    <input type="hidden" name="orderValue" value="5"> <!--post to sql-->
                    <input type="hidden" name="orderName" value="Sheep"> <!--show order's name -->
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
        <!--Salmon-->
        <div class="col">
          <form id="salmon" action="order.php" method="POST">
          <div class="row" onclick="document.getElementById('salmon').submit();" style="cursor:pointer"> <!-- onclick from form's id -->
              <div class="Menu_img" id="imgSalmonMenu">
                <div class="row logo-container-div align-items-center justify-content-center">
                  <div class="logo-wrapper col-auto ">
                    <h1 class="nameMenu">Salmon</h1>
                    <h5 id="price">350</h5>
                    <input type="hidden" name="orderValue" value="6"> <!--post to sql-->
                    <input type="hidden" name="orderName" value="Salmon"> <!--show order's name -->
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
    <br><br><br>
    <div class="img-fluid">
      <br><br>
      <div class="container">
         <h1 id="contact">Contact</h1>
         <div id="contact">
            <p>
              Lorem ipsum dolor sit amet, consectetur 
              adipiscing elit,<br> sed do eiusmod tempor 
              incididunt ut labore et dolore magna<br> aliqua.
              Ut enim ad minim veniam, quis nostrud
              exercitation<br> ullamco laboris nisi ut aliquip 
              ex ea commodo consequat. 
            </p>
         </div>
         <br>
    </div>
    <style>
      .img-fluid{
        height: max-content;
        width: auto;
        background-image: linear-gradient(150deg, rgba(89, 2, 2, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/s.jpg");
        background-position: 0%, 0%, 50%, 50%;
        background-attachment: scroll, fixed;
        background-size: auto, cover;
      }
      .logo-wrapper {
        font-family: 'Raleway', sans-serif;
        padding: 150px 30px;
        text-align:center;
        position: relative;
      }
      .nameMenu {
        padding: 20px 80px;
        color: black;
      }
      #price{
        color: #313940;
      }
      #contact{
        color: whitesmoke;
      }
      #imgPorkMenu{
        height: 430px;
        width: 300px;
        background-image: linear-gradient(150deg, rgba(191, 118, 54, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/porkk.jpg");
        background-position: 50%, 50%;
        background-size: contain, cover;
      }
      #imgChickenMenu{
        height: 430px;
        width: 300px;
        background-image: linear-gradient(150deg, rgba(191, 118, 54, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/chicken.jpg");
        background-position: 50%, 50%;
        background-size: contain, cover;
      }
      #imgBeefMenu{
        height: 430px;
        width: 300px;
        background-image: linear-gradient(150deg, rgba(191, 118, 54, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/beef.jpg");
        background-position: 50%, 50%;
        background-size: contain, cover;
      }
      #imgFishMenu{
        height: 430px;
        width: 300px;
        background-image: linear-gradient(150deg, rgba(191, 118, 54, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/fish.jpg");
        background-position: 50%, 50%;
        background-size: contain, cover;
      }
      #imgSheepMenu{
        height: 430px;
        width: 300px;
        background-image: linear-gradient(150deg, rgba(191, 118, 54, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/sheep.jpg");
        background-position: 50%, 50%;
        background-size: contain, cover;
      }
      #imgSalmonMenu{
        height: 430px;
        width: 300px;
        background-image: linear-gradient(150deg, rgba(191, 118, 54, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/salmon.jpg");
        background-position: 50%, 50%;
        background-size: contain, cover;
      }
    </style>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
  </body>
</html>