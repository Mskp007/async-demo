mvn clean install
docker build -t springio/raspberrypimicrok8sjava .
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl apply -f ingress.yaml
