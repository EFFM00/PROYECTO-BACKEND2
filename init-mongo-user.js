db.createUser(
	{
	    user: "root",
	    pwd: "root",
	    roles: [
		{
		    role: "readWrite",
		    db: "db-users-2"
		}
	    ]
	}
);
