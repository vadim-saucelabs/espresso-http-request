# espresso-gradle-example

Example setup for using TestObject Gradle Plugin with Espresso. To build the app and test .apk files, run:

	./gradlew assemble
	./gradlew assembleAndroidTest
	
The .apk files app-debug.apk and app-debug-androidTest-unaligned.apk will be located in `app/build/outputs/apk/`.

Tests are then triggered using [espresso-runner](https://github.com/testobject/espresso-runner).
