// Es lässt sich erkennen, dass die Grammatik bereits einer Art 
// |\textbf{Adapter}| für den |\textbf{Domänen-Code}| darstellt
deployment("nginx") {
    tags("personal", "website")
    replicas = 3

    container("proxy") {
        image("nginx", "latest")
    }
}
