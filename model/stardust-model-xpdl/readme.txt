This is a Maven project for producing a JAR file suitable for use outside of
OSGi/Equinox.

Please be aware that the project's source folders are just links to the peer
PDE project's src/ folder, with resources filters applied to mimic Maven
conventions.

As far as known, both projects, this one and it's peer PDE project, may be
open at the same time in one Eclipse workspace without problems.
However, as both projects share the same source files, it might be confusing
that Java classes seem to be present twice. A strategy to avoid this confusion
might be just importing/opening the PDE project or the JAR project.