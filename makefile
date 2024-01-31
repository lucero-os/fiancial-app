setup:
	@make postgres-vol-directory
	@make db
	@make db-entities
postgres-vol-directory:
	mkdir -p postgres_data
db:
	docker run \
	  --name=my_postgres_container \
	  --hostname=my-postgres-local \
	  --mac-address=02:42:ac:11:00:03 \
	  -e POSTGRES_USER=financial_project_user \
	  -e POSTGRES_PASSWORD=secret \
	  -e POSTGRES_DB=financial_project_db \
	  -v $(shell pwd)/postgres_data:/var/lib/postgresql/data \
	  -p 5432:5432 \
	  --restart=no \
	  --label='org.opencontainers.image.ref.name=postgres' \
	  --label='org.opencontainers.image.version=14.1' \
	  -d postgres:latest
db-entities:
	docker exec -i my_postgres_container psql -U financial_project_user -d financial_project_db < ./sql/financial_app_objects.sql