HUB_URL=http://localhost:4444/grid/register
CHROME_DRIVER_LOC=/home/osboxes/selenium/chromedriver
java -jar selenium-server-standalone-3.13.0.jar -role hub &
java -jar selenium-server-standalone-3.13.0.jar -role node -hub $HUB_URL -port 5556 &
java -jar selenium-server-standalone-3.13.0.jar -role node -hub $HUB_URL -port 5557 &

