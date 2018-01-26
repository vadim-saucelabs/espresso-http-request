FROM ubuntu:14.04

# Install java8
RUN apt-get update && \
  apt-get install -y software-properties-common && \
  add-apt-repository -y ppa:webupd8team/java && \
  (echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections) && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  apt-get clean && \
  rm -fr /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Install other apt-get dependencies
RUN apt-get update && apt-get install -y zip lib32stdc++6 lib32z1&& \
    apt-get clean

# Install Android SDK
RUN wget -O androidsdk.zip https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip && \
    unzip androidsdk.zip -d /opt/android-sdk-linux && \
    rm androidsdk.zip
ENV ANDROID_HOME /opt/android-sdk-linux

# Install Android platforms
RUN yes | ${ANDROID_HOME}/tools/bin/sdkmanager --licenses && \
    ${ANDROID_HOME}/tools/bin/sdkmanager "platforms;android-21" && \
    ${ANDROID_HOME}/tools/bin/sdkmanager "build-tools;23.0.1"