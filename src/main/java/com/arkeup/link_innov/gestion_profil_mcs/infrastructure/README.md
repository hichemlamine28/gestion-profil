# Swagger

Pour rendre la génération des collections des WS automatique, il faut rajouter les configurations suivantes sur chaque ws selon son type :

## POST Example
	@ApiOperation(value = "Create Entity", notes = "This WS is used to add new Entity.")
	@PostMapping(path = "/create")
	public EntityDTO create(@ApiParam(name = "EntityDTO",
			value = "{\"description\":\"description example\",\"lastName\":\"lastName example\",\"firstName\":\"firstName example\"}",
			required = true) @Valid @RequestBody EntityDTO entityDTO) {
		// do something
	}
	
## PUT Example
	@ApiOperation(value = "Update Entity", notes = "This WS is used to update existing Entity.")
	@PutMapping(path = "/update")
	public EntityDTO update(@ApiParam(name = "EntityDTO",
			value = "{\"lastName\":\"lastName updated\",\"firstName\":\"firstName updated\"}",
			required = true) @Valid @RequestBody EntityDTO entityDTO) {
		// do something
	}
	
## GET Example
	@ApiOperation(value = "Get List", notes = "This WS is used to get list of EntityDTO.")
	@GetMapping(path = "/getAll")
	public List<EntityDTO> get(@RequestParam("id") String id) {
		// do something
	}
	
## DELETE Example
	@ApiOperation(value = "Delete Entity", notes = "This WS is used to delete Entity by Id.")
	@DeleteMapping(path = "/delete/{id}")
	public void delete( @PathVariable("id") String id) {
		// do something
	}
	
* Une fois l'application lancer il faut vérifier que le swagger est UP en checkant l'url suivante : http://localhost:8080/v2/api-docs
* Utilisez un client REST comme postman pour importer la collection des WS en utilisant url : http://localhost:8080/v2/api-docs
