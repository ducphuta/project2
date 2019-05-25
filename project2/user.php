<?php 

$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT * FROM user";

$data = mysqli_query($connect, $query);

class User
{
	
	function User($account, $password, $name)
	{
		$this->Acc = $account;
		$this->Pass = $password;
		$this->Name = $name;
		
	}
}
$user = array();


while ($row = mysqli_fetch_row($data)) {
	array_push($user, new User($row['0'], $row['1'], $row['2']));
}

echo json_encode($user);



 ?>