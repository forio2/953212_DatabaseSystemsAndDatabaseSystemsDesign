<!--(Apply! Button)(21) to register.php-->
<!--(SIGN IN Button)(33) to login.php-->
<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <title>Steak Justice</title>
  </head>
  <body onload="start()">
    <!-- Register button-->
    <nav class="navbar navbar-dark bg-dark">
      <a class="navbar-brand"></a>
      <form class="form-inline">
        <h4 class="text-white">Join our members</h4>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <button onclick="window.location.href = 'register.php'" type="button" class="btn btn-outline-success my-2 my-sm-0 " type="submit">Apply!</button>
      </form>
    </nav>
    <!-- Login button-->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a href="index.php"><span class="navbar-brand mb-0 h1">Steak Justice</span></a> 
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
        </div>
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <button onclick="showThai()" type="button" class="btn btn-outline-success btn-sm">TH</button>
            <button onclick="showEng()" type="button" class="btn btn-outline-success btn-sm">EN</button>
            <button onclick="window.location.href = 'login.php'" type="button" class="btn btn-outline-success btn-sm">SIGN IN</button>
        </li>
      </ul>
    </nav>
    <!--content-->
    <div class="img-fluid">
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid" style="width: 500px; ">
          <!--Eng version-->
          <div class="container" id="engResult">
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
          <!--Thai version-->
          <div class="container" id="thaiResult">
            <h1>สเต๊ก จัสติซ</h1>
            <p>
              สตรอว์เบอร์รีสติ๊กเกอร์สหัชญาณ
              แฟรนไชส์วอลล์ขั้นตอน<br> ซินโดรมหมั่นโถว 
              สแควร์เอาท์ดอร์ออยล์<br>
              สเตเดียมเจี๊ยวนายพราน ช็อปเปอร์
              อาร์พีจีเทคโน<br>แครตมิวสิค 
              บอดี้ซื่อบื้อปักขคณนา 
              ภควัมปติ<br>
            </p>
          </div>
        </div>
      </div>
    </div>  
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
        padding: 220px 30px;
        text-align:center;
        position: relative;
      }
    </style>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <!--js-->
    <script src="language.js"></script>
  </body>
</html>