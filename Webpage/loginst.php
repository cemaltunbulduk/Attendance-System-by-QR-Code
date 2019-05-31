<?php 
require "connect.php";
$json = array();
$user_name = $_POST["username"];
$user_pass = $_POST["password"];
$json["girilenKullanici"] = $user_name;
$json["girilenSifre"] = $user_pass;

$query = "SELECT * FROM student WHERE studentNum = '$user_name' AND password = '$user_pass'"; 
$result = mysqli_query($conn ,$query);
$a = mysqli_num_rows($result);
$json["numrows"] = $a;
if(mysqli_num_rows($result) > 0) {

$json["success"] = true;
echo json_encode($json);
}
else {
$json["success"] = false;
$json["message"] = "Wrong student number or password";
echo json_encode($json);
}
 
?>