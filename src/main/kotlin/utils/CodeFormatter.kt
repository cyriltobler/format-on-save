package utils

import com.intellij.codeInsight.actions.OptimizeImportsProcessor
import com.intellij.codeInsight.actions.RearrangeCodeProcessor
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.ProjectLocator
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.codeStyle.CodeStyleManager

fun formatCode(document: Document) {
    val virtualFile = FileDocumentManager.getInstance().getFile(document) ?: return
    val project = ProjectLocator.getInstance().guessProjectForFile(virtualFile) ?: return

    PsiDocumentManager.getInstance(project).commitDocument(document)
    val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document) ?: return

    WriteCommandAction.runWriteCommandAction(project, "Reformat Code", null, {
        CodeStyleManager.getInstance(project).reformatText(
          psiFile,
          0,
          psiFile.textLength
        )
        OptimizeImportsProcessor(project, psiFile).run()
        RearrangeCodeProcessor(psiFile, emptyArray()).run()
    }, psiFile)
}
