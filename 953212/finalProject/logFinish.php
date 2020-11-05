<!--when chick button 'Login' From login.php-->
<!--(system) go to main.php(status = M) or mainAdmin.php(status = A)-->
<?php 
session_start();
$link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever"); //connect to PhpMyAdmin
$Username = $_POST['Username'];
$Password = $_POST['Password'];
if (($Username == NULL) || ($Password == NULL)){
?>
<script language = "javascript">
  alert('Please input the right input');
  window.location.href='login.php';
</script>";
<?php
}
$sql = "SELECT * FROM Customers
WHERE usernameCustomer = '$Username' AND passwordCustomer = '$Password'";
$objQuery = mysqli_query($link, $sql); //query
$Result = mysqli_fetch_array($objQuery); //call 1st
if(!$Result){
?>
  <script language = "javascript">
    alert('Please input the right input1  ');
    window.location.href='login.php';
  </script>";
<?php
}
else{
  $_SESSION['ID'] = $Result['noCustomer'];
  $_SESSION['Name'] = $Result['nameCustomer'];
  $_SESSION['Username'] = $Result['usernameCustomer'];
  $_SESSION["Password"] = $Result['passwordCustomer'];
  $_SESSION['Email'] = $Result['emailCustomer'];
  $_SESSION['Status'] = $Result['status'];
?>
<?php
if(($Result["status"] == 'M')){ //Member
?>
  <script language = "javascript">
    window.location.href='main.php';
  </script>";
<?php
}
else if(($Result["status"] == 'A')){ //Admin
?>
  <script language = "javascript">
    window.location.href='mainAdmin.php';
  </script>";
<?php
}
?>
<?php
}
?>