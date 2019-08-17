name := "bonify"

version := "0.1"

scalaVersion := "2.12.6" 

lazy val akkaHttpVersion = "10.1.7"
lazy val akkaVersion     = "2.5.19"
val scalaTestVersion     = "3.0.5"

libraryDependencies ++= Seq(
  "com.github.mauricio"    %% "postgresql-async"     % "0.2.21",
  "com.typesafe"           % "config"                % "1.3.4",
  "com.typesafe.akka"      %% "akka-http"            % "10.1.7",
  "com.typesafe.akka"      %% "akka-stream"          % "2.5.19",
  "com.typesafe.akka"      %% "akka-http-testkit"    % akkaHttpVersion   % Test,
  "com.typesafe.akka"      %% "akka-testkit"         % akkaVersion       % Test,
  "org.scalatest"          %%  "scalatest"           % scalaTestVersion  % Test

)