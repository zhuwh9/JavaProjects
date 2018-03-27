<?php

function validate_user() {
	$hdr = getallheaders();
	$CI = &get_instance();
	$CI->db->where("user_id", (int)$hdr["user-id"]);
	$CI->db->where("accesstoken", (int)$hdr["access-token"]);
	if ($row = $CI->db->get("users")->row()) {
		return $row;
	}
	return false;
}