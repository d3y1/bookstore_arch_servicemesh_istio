# BookStore后端：以Istio服务网格实现

## 需求场景

Kubernetes微服务架构痛点：靠着Kubernetes本身的虚拟化基础设施，难以做到精细化的服务治理，譬如熔断、流控、观测等等，可以采用Istio服务网格架构，通过边车代理(sidecar)方式，让开发人员只需专注于业务需求，无需过多关注分布式的技术，又能通过代码灵活解决分布式的可配置、安全和可观测性问题。

## 运行程序

在已经部署Kubernetes与Istio的前提下，通过以下几种途径，可以运行程序：

- 在Kubernetes无Sidecar状态下运行：

  ```bash
  # 进入工程目录
  $ cd bookstore_arch_servicemesh_istio
  
  # Kubernetes without Envoy资源描述文件
  $ kubectl apply -f bookstore-dev.yml
  ```

  服务地址：[http://localhost](http://localhost)

  注意：如果你使用 Docker Desktop 作为容器驱动程序运行 minikube（--vm-driver=docker），则需要 minikube 隧道。 这是因为 Docker Desktop 内的容器与主机隔离。
  ```bash
  # 生成访问地址
  $ minikube service istio-ingressgateway --url -n istio-system
  http://127.0.0.1:60428
  http://127.0.0.1:60429
  http://127.0.0.1:60430

  ❗  Because you are using a Docker driver on darwin, the terminal needs to be open to run it.
  ```
  
- 在Istio服务网格环境上运行：

  ```bash
  # 进入工程目录
  $ cd bookstore_arch_servicemesh_istio
  
  # Kubernetes with Envoy 资源描述文件
  $ kubectl apply -f bookstore.yml
  ```

  服务地址：[http://localhost](http://localhost)

- 通过Skaffold在命令行或IDE中以调试方式运行：

  ``` bash
  # 进入工程目录
  $ cd bookstore_arch_servicemesh_istio
  
  # 编译打包
  $ mvn clean package
  
  # 启动Skaffold
  # 此时将会自动打包Docker镜像，并部署到Kubernetes中
  $ skaffold dev
  ```
