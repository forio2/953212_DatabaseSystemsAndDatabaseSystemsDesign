<?php 
session_start();
$link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever");
$sql = "INSERT INTO Payment
(noItem, noCustomer, total)VALUES
(NULL, '{$_SESSION['ID']}', '{$_SESSION['total']}')";
$objQuery = mysqli_query($link, $sql);
?>
<script>
  window.location.href='main.php';
</script>;