<?php 
session_start();
$link = mysqli_connect("localhost","root", "", "SteakJustice") or die ("Can't MySQL sever");
$Menu = $_POST['addName'];
$Quantity = $_POST['Qty'];
if (($Quantity == NULL || $Quantity < 1)){
?>
<script language = "javascript">
  alert('Please input the right input');
  window.location.href='main.php';
</script>";
<?php
}
else{
  $sql = "INSERT INTO Orders
(noOrder, quantity, noMenu, noCustomer)VALUES
(NULL, '$Quantity', '$Menu', '{$_SESSION['ID']}')";
$objQuery = mysqli_query($link, $sql);
?>
<script>
  window.location.href='main.php';
</script>;
<?php
}
?>