<?php
require "connect.php";

$json = array();

if(isset($_POST["student_number"]) && isset($_POST["date"])){
	$number = $_POST["student_number"];
	$date = $_POST["date"];
	$query = "SELECT * FROM attendance WHERE student_number = '$number' AND date = '$date'";

	$result = mysqli_query($conn, $query);
	if(mysqli_num_rows($result) == 0 ){

		$insert = "INSERT INTO attendance(student_number, date) VALUES('$number', '$date')";

		$result = mysqli_query($conn, $insert);

		if($result){
			$json["success"] = true;
			echo json_encode($json);
		}else{
			$json["success"] = false;
			$json["message"] = "An error has been occured during the insertion on database";
			echo json_encode($json);
		}
	}
	else{
		$json["success"] = false;
		$json["message"] = "You have been already registered for this date";
		echo json_encode($json);
	}

}
else {
	$json["success"] = false;
	$json["message"] = "Empty necessary data";
	echo json_encode($json);
}



?>