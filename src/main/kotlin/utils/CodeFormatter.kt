package utils

import com.intellij.codeInsight.actions.OptimizeImportsProcessor
import com.intellij.codeInsight.actions.RearrangeCodeProcessor
import com.intellij.codeInsight.actions.ReformatCodeProcessor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

fun formatFiles(project: Project, psiFiles: List<PsiFile>) {
    val psiArray = psiFiles.toTypedArray()

    ReformatCodeProcessor(project, psiArray, null, false).run()
    OptimizeImportsProcessor(project, psiArray, null).run()
    RearrangeCodeProcessor(project, psiArray, "Reformat on Save", null).run()
}
