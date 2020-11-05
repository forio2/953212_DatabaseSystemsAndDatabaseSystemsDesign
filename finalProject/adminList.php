<?php
session_start();
if ($_SESSION['Username'] == "") {
  echo "<script>
          alert('Please Login!');
          window.location.href='login.php';
        </script>";
  exit();
}
$link = mysqli_connect("localhost", "root", "", "SteakJustice") or die("Can't MySQL sever");
$sql = "SELECT * FROM Customers WHERE usernameCustomer = '" . $_SESSION['Username'] . "' ";
$objQuery = mysqli_query($link, $sql);
$Result = mysqli_fetch_array($objQuery);

?>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
  <style>
    .img-fluid {
      height: max-content;
      width: auto;
      background-image: linear-gradient(150deg, rgba(89, 2, 2, 0.6) 30%, rgba(191, 118, 54, 0.7)), url("assets/s.jpg");
      background-position: 0%, 0%, 50%, 50%;
      background-attachment: scroll, fixed;
      background-size: auto, cover;
    }

    .logo-wrapper {
      font-family: 'Raleway', sans-serif;
      text-align: center;
      padding: 260px;
      position: relative;
    }
  </style>
</head>

<body>
  <div class="img-fluid">
    <!--show Admin-->
    <div class="row logo-container-div justify-content-center">
      <div class="logo-wrapper col-auto">
        <div class="jumbotron jumbotron-fluid rounded shadow-lg p-3 mb-5 rounded" style="width: 700px;"> <!--jumbotron, rounded and shadow -->
            <div class="container">
                <!--Select Admin-->
                <?php
                $table = "SELECT *  FROM Customers 
                WHERE status = 'A'
                group by noCustomer";
                $objQuery2 = mysqli_query($link, $table) or die( mysqli_error($link));
                ?>
                <table class="table">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">
                                No.
                            </th>
                            <th scope="col" >
                                Name
                            </th>
                            <th scope="col">
                                Email
                            </th>
                            <th scope="col">
                                Member
                            </th>
                            <th scope="col"></th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                        <?php
                    while ($Result2 = mysqli_fetch_array($objQuery2)) {
                        ?>
                    <tbody>
                        <tr>
                            <td>
                                <?php echo $Result2["usernameCustomer"]; ?>
                            </td>
                            <td>
                                <?php echo $Result2["nameCustomer"]; ?>
                            </td>
                            <td>
                                <?php echo $Result2["emailCustomer"]; ?>
                            </td>
                            <!--Update status-->
                            <form id="demote" action="demoteToMember.php" method="POST">
                            <td>
                                <button 
                                type="submit"
                                name="degrade"
                                onclick="document.getElementById('update');" 
                                class="btn btn-warning btn" 
                                style="width: 80px"
                                value="<?php echo $Result2["noCustomer"]; ?>"
                                >
                                    Demote
                                </button>
                            </td>
                            </form>
                            <!--delete member-->
                            <form id="delete" action="deleteMember2.php" method="POST">
                                <td>
                                    <button 
                                    type="submit"
                                    name="deleteMember"
                                    onclick="document.getElementById('delete');" 
                                    class="btn btn-danger btn" 
                                    style="width: 80px"
                                    value="<?php echo $Result2["noCustomer"]; ?>"
                                    >
                                        Delete
                                    </button>
                                </td>
                            </form>
                        </tr>
                        <?php  
                    }
                        ?>
                    </tbody>
            </table>
            <!--Total-->
            <?php
            $table2 = "SELECT sum(Orders.quantity*price) as total 
            FROM Customers INNER JOIN Orders
            ON Customers.noCustomer = Orders.noCustomer
            INNER JOIN  Menu
            ON Orders.noMenu = Menu.noMenu
            WHERE Customers.noCustomer = '" . $_SESSION['ID'] . "'
            group by Customers.nameCustomer;";
            $objQuery3 = mysqli_query($link, $table2) or die(mysqli_error($link)) ;
            ?>
          </div>
            <div class="form-group">
                <?php
                if(($Result["status"] == 'A')){
                ?>
                    <button type="button" onclick="window.location.href = 'profileAdmin.php'" class="btn btn-dark btn-lg" style="width: 200px">Back</button>
                <?php
                }else if(($Result["status"] == 'M')){
                ?>
                    <button type="button" onclick="window.location.href = 'profile.php'" class="btn btn-dark btn-lg" style="width: 200px">Back</button>
                <?php
                }
                ?>
            </div>
        </div>
      </div>
    </div>
  </div>
</body>

</html>