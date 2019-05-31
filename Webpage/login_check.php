<?php  
require('connect.php');

if (isset($_POST['username']) && isset($_POST['password'])){
	
// Assigning POST values to variables.
$username = $_POST['username'];
$password = $_POST['password'];

$query = "SELECT * FROM teacher WHERE email = '$username' AND password = '$password'";
$result = mysqli_query($conn, $query);
if(mysqli_num_rows($result) > 0){
	setcookie('lecturer', $username, time() + 3600);
	header("location:index.php");
}
else{
	header("location:login.php");
}


}else{
echo "Error: Wrong username or password! Redirecting to Login Page";
header("Refresh:2; url=login.php");
}

?>