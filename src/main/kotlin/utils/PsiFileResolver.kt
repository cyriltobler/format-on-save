package utils

import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile

fun resolveUnsavedPsiFiles(project: Project): List<PsiFile> {
    val unsavedDocuments = FileDocumentManager.getInstance().unsavedDocuments.toList()
    if (unsavedDocuments.isEmpty()) return emptyList()

    val psiDocumentManager = PsiDocumentManager.getInstance(project)
    val psiFiles = unsavedDocuments.mapNotNull { psiDocumentManager.getPsiFile(it) }
    if (psiFiles.isEmpty()) return emptyList()

    psiDocumentManager.commitAllDocuments()
    return psiFiles
}

fun resolvePsiFile(project: Project, document: Document): PsiFile? {
    val psiDocumentManager = PsiDocumentManager.getInstance(project)
    val psiFile = psiDocumentManager.getPsiFile(document) ?: return null

    psiDocumentManager.commitDocument(document)
    return psiFile
}