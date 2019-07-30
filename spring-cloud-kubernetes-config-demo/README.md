# Spring Cloud Kubernetes Config

Minimal setup to demonstrate using the Spring Cloud Kubernetes Config dependency to facilitate external configuration from Kubernetes ConfigMap or Secret api objects.

## Setup
First, ensure you have Minikube or a Kuberentes cluster available to test with and you are familiar with accessing applications via an exposed node port within Kubernetes. Refer to the [official documentation on configuring a pod with cofigmaps](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/) for additional information including [how to set up minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/) or use an alternative.

Also ensure you have Maven and Java 8 installed. Some tweaks to dependencies may be required for Java 9+ since `java.xml.bind ` was removed in later versions of Java.

## Build
We will be utilizing minikube, and minikube's build environment to facilitate this demo, however it's possible to use the `latest` version of the demo app from [my docker hub](https://cloud.docker.com/u/jmlw/repository/list). In your terminal run `eval $(minikube docker-env)` to configure your current session to use minikube's docker engine to build and store images. This is required if you intend to use local images only. Otherwise, the `PullPolicy` in the kubernetes deployment can be changed to `Always` and an image from a remote docker repository can be used.

Maven is configured to build the docker image using the packaged jar by running:
```bash
./mvnw clean compile package dockerfile:build
```

## Deploy to (Mini)Kubernetes
To deploy, ensure you kubectl configured, and simply run `kubectl apply -f deployments/`. This will apply all of the manifest files in the directory: 

- service account for the container
- role defining kubenertes API access levels
- role binding to attache the role to the service account
- deployment defining a template for the pod(s) to be created utilizing the image we've built
- service to expose our app as a NodePort in our Kuberentes Cluster
- configmap for our application which includes an override for the default value provided in our spring boot application.yml

## Verify
Verify that our app is actually using the configuration from our configmap, fetch the logs of our pod as a one liner:

```bash
kubectl logs "$(kubectl get pods | grep demo-deployment | awk '{print $1}')"
```

Now verify that our endpoint is accessible and returns our config value:

```bash
curl "$(minikube service demo --url)"
# expected output:
# Configuration from Kubernetes!
```
