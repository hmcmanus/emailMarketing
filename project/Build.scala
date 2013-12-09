import sbt._
import Keys._

object ApplicationBuild extends Build {

  val appName         = "emailMarketing"
  val appVersion      = "1.0.0-SNAPSHOT"

  val appDependencies = Seq(
    "org.mindrot" % "jbcrypt" % "0.3m"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.url("sbt-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)
  )
}