################# define variables #################

LIBS=axislibs
WSDL_DIR=./wsdls
NS1=http://rtsisp01.riits.net/ISP/services
SPOOLDIR=./classes
SOURCEDIR=.
JAVAHOME=/home/ubuntu/workspaces/Waycare/isploadtest/java
CLASSPATH="$(LIBS)/axis.jar:$(LIBS)/commons-logging.jar:$(LIBS)/log4j-1.2.8.jar:$(LIBS)/commons-discovery.jar:$(LIBS)/saaj.jar:$(LIBS)/jaxrpc.jar:$(LIBS)/wsdl4j.jar"
all: clean jars

jars:
	$(JAVAHOME)/bin/javac.exe -g -classpath $(CLASSPATH) \
	-d $(SPOOLDIR)  -sourcepath $(SOURCEDIR) \
	-deprecation \
        *.java 
wsdlhelp:
	$(JAVAHOME)/bin/java -classpath $(CLASSPATH) \
	org.apache.axis.wsdl.WSDL2Java -help
wsdl2j:
	
	$(JAVAHOME)/bin/java.exe -classpath $(CLASSPATH) \
        org.apache.axis.wsdl.WSDL2Java \
                -T1.1 \
		-N$(NS1)/ISP_CongestionService=com.nateng.riits.isp.vds \
		$(WSDL_DIR)/ISP_CongestionService.wsdl
	
clean:
	rm -rf $(SPOOLDIR); mkdir $(SPOOLDIR)

run:
	$(JAVAHOME)/bin/java -Xmx512m -DentityExpansionLimit=2000000 -classpath ./classes:.:$(CLASSPATH) \
		ISPTest 1 60000 dump pretty isptest isptest isp

install:

distribution:
