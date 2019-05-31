<center> QR Code Attendance time is up.  <br> Attended Students <br>
<?php
require "connect.php";
$query = "SELECT * FROM attendance";
$sql = mysqli_query($conn,$query);

while( $result=mysqli_fetch_array($sql,MYSQLI_NUM)){
echo $result[1].' '.$result[2].'<br>';
}




echo "<a href=logout.php>Logout</a></center>";

?>
</center>