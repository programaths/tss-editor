package be.programaths.idea.plugins.editors.tss

import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.source.html.HtmlFileImpl
import com.intellij.psi.impl.source.xml.XmlAttributeImpl
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.indexing.FileBasedIndex


class TSSUtil {
    companion object {
        @JvmStatic
        fun findIds(project: Project) : List<String> {
            val files = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, HtmlFileType.INSTANCE,
                    GlobalSearchScope.allScope(project))
            val completions = mutableListOf<String>()
            files.forEach {
                val htmlFile = PsiManager.getInstance(project).findFile(it) as? HtmlFileImpl ?: return@forEach
                val attrs = PsiTreeUtil.findChildrenOfType(htmlFile, XmlAttributeImpl::class.java)
                if (attrs.isEmpty()) return@forEach

                completions.addAll(
                        attrs.mapNotNull {
                            if (it.localName == "id") it.value else null
                        }
                )
            }
            return completions
        }

        @JvmStatic
        fun findClasses(project: Project) : List<String> {
            val files = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, HtmlFileType.INSTANCE,
                    GlobalSearchScope.allScope(project))
            val completions = mutableListOf<String>()
            files.forEach {
                val htmlFile = PsiManager.getInstance(project).findFile(it) as? HtmlFileImpl ?: return@forEach
                val attrs = PsiTreeUtil.findChildrenOfType(htmlFile, XmlAttributeImpl::class.java)
                if (attrs.isEmpty()) return@forEach

                completions.addAll(
                        attrs.mapNotNull {
                            if (it.localName == "class") it.value?.run { this.split(", ") } else null
                        }.flatten()
                )
            }
            return completions
        }

        @JvmStatic
        fun findIdClassesElements(project: Project) : List<String> {
            val files = FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, HtmlFileType.INSTANCE,
                    GlobalSearchScope.allScope(project))
            val completions = mutableListOf<String>()
            files.forEach {
                val htmlFile = PsiManager.getInstance(project).findFile(it) as? HtmlFileImpl ?: return@forEach
                val attrs = PsiTreeUtil.findChildrenOfType(htmlFile, XmlAttributeImpl::class.java)
                if (attrs.isEmpty()) return@forEach

                completions.addAll(
                        attrs.mapNotNull {
                            when (it.localName) {
                                "id" -> it.value?.run { this.split(", ").map { "#$it" } }
                                "class" -> it.value?.run { this.split(", ").map { ".$it" } }
                                else -> null
                            }
                        }.flatten()
                )
            }
            with(completions){
                add("div")
                add("td")
                add("tr")
                add("form")
                add("ul")
                add("ol")
                add("li")
                add("span")
            }
            return completions
        }
    }
}