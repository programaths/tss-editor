package be.programaths.idea.plugins.editors.tss

import be.programaths.idea.plugins.editors.tss.psi.TSSTypes
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.or
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.ProcessingContext

class TSSCompletionContributor : CompletionContributor() {


    init {
        extend(
                CompletionType.BASIC,
                psiElement().afterLeaf(psiElement(TSSTypes.POUND)).withAncestor(2,or(psiElement(TSSTypes.RULE), psiElement(TSSTypes.SELECTOR))),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
                        result.addAllElements(TSSUtil.findIds(parameters.originalFile.project).map(LookupElementBuilder::create))
                    }
                }
        )
        extend(
                CompletionType.BASIC,
                psiElement().afterLeaf(psiElement(TSSTypes.DOT)).withAncestor(2,or(psiElement(TSSTypes.RULE), psiElement(TSSTypes.SELECTOR))),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
                        result.addAllElements(TSSUtil.findClasses(parameters.originalFile.project).map(LookupElementBuilder::create))
                    }
                }
        )
        extend(
                CompletionType.BASIC,
                psiElement().andNot(or(psiElement().afterLeaf(psiElement(TSSTypes.POUND)), psiElement().afterLeaf(psiElement(TSSTypes.DOT)))).withAncestor(2,or(psiElement(TSSTypes.RULE), psiElement(TSSTypes.SELECTOR))),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
                        result.addAllElements(TSSUtil.findIdClassesElements(parameters.originalFile.project).map(LookupElementBuilder::create))
                    }
                }
        )
        extend(
                CompletionType.BASIC,
                psiElement().withAncestor(2,psiElement(TSSTypes.DEC)),
                object : CompletionProvider<CompletionParameters>() {
                    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
                        result.addElement(LookupElementBuilder.create("repeat:").withTailText(";"))
                        result.addElement(LookupElementBuilder.create("content:").withTailText(";"))
                    }
                }
        )
    }
}