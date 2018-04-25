<?php

if ($row = validate_user()) {
	$this->db->where("user_id", $row->user_id);
	$this->db->order_by("modify", "desc");

	$result = $this->db->get("notes");
	$data = [
		"status" => 0,
		"data" => []
	];
	while($row = $result->unbuffered_row()) {
		$data['data'][] =
			[
				"id"       => $row->note_id,
				"title"       => $row->title,
				"last_modify" => $row->modify,
				"content"     => $row->text,
			];
	}
	echo json_encode($data);
} else {

	echo json_encode([
		"status" => 500
		]);
}

