<?php
$request_data = json_decode($this->input->post("request_data"));
$username = $request_data->username;
$password = sha1($request_data->password);
$email = $request_data->email;
$this->db->where("username", $username);

$result = $this->db->get("users");
if ($row = $result->row()) {
	echo json_encode( [
		"status"  => 2402,
		"message" => "用户已存在"
	] );
} else {
	$data = [
		"username" => $username,
		"email"    => $email,
		"password" => $password
	];
	$this->db->insert( "users", $data );
	echo json_encode( [
		"status"       => 0
	] );
}