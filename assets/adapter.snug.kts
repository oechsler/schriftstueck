// Operationen wären nur Top-Level möglich
// Dieser Ansatz macht die Sprache nur unnötig Komplex.
deployment(
    DeploymentAdapter(
        "nginx",
        TagAdapter("personal", "website"),
        3,
        ContainerAdapter(
            "website",
            ImageAdapter("nginx", "latest")
        )
    )
)