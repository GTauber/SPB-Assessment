run-deps: ## Run dependencies (MySql)
	cd Docker/compose && docker-compose -f docker-compose.yml up

deploy: ## Deploy the application
	./build_and_deploy.sh