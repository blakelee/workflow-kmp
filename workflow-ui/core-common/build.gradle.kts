import com.squareup.workflow1.buildsrc.iosWithSimulatorArm64

plugins {
  id("kotlin-multiplatform")
  id("published")
}

kotlin {
  val targets = project.findProperty("workflow.targets") ?: "kmp"
  if (targets == "kmp" || targets == "ios") {
    iosWithSimulatorArm64()
  }
  if (targets == "kmp" || targets == "jvm") {
    jvm { withJava() }
  }
  if (targets == "kmp" || targets == "js") {
    js(IR) { browser() }
  }

  sourceSets {
    jvmTest.dependencies {
      implementation(libs.junit)
      implementation(libs.kotlin.test.core)
      implementation(libs.kotlin.test.jdk)
      implementation(libs.kotlinx.coroutines.test)
      implementation(libs.truth)
      implementation(libs.turbine)
    }
  }
}


dependencies {
  commonMainApi(libs.kotlin.jdk6)
  commonMainApi(libs.kotlinx.coroutines.core)
  commonMainApi(libs.squareup.okio)
}
