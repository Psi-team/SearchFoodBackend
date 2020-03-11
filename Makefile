# Makefile 

# VARS 
MVN=mvn 
PLUGIN=spring-boot
GOAL=run

# make 
build: 
	$(MVN) $(PLUGIN):$(GOAL) 
