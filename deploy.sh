if [ $? -ne 0 ]; then
  exit -1
fi

echo "No deployment necessary, installing..."
mvn install --settings target/CM/settings.xml


