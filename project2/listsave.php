<?php 

$connect = mysqli_connect("localhost","root","123456","project2");
mysqli_query($connect, "SET NAMES 'utf8'");

$query = "SELECT ID, acc, salary,address, place, content, date, time, name, account FROM post, save WHERE `post`.`ID` = `save`.`IDpost`";

$data = mysqli_query($connect, $query);

class Post {

	function Post($id, $acc, $salary,$address, $place, $content, $date, $time, $name, $account){
		$this->id = $id;
		$this->acc = $acc;
		$this->salary = $salary;
		$this->address = $address;
		$this->place = $place;
		$this->content = $content;
		$this->date = $date;
		$this->time = $time;
		$this->name = $name;
		$this->account = $account;
	}
}

$post = array();

while ($row = mysqli_fetch_row($data)) {
	array_push($post, new Post($row['0'], $row['1'], $row['2'], $row['3'], $row['4'], $row['5'], $row['6'], $row['7'], $row['8'], $row['9']));
}

echo json_encode($post);

 ?>