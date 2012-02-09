/*******************************************************************************
 * Copyright (c) 2000, 2011 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Philippe Ombredanne <pombredanne@nexb.com> - https://bugs.eclipse.org/bugs/show_bug.cgi?id=150989
 *     Sungard - Adapted to meet special requirements for syntax highlighting for the Javascript Editor 
 *******************************************************************************/
package org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.widgets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.stardust.modeling.transformation.messaging.modeling.application.transformation.MessageTransformationController;
import org.eclipse.swt.SWT;
import org.eclipse.wst.jsdt.core.JavaScriptCore;
import org.eclipse.wst.jsdt.internal.ui.javaeditor.SemanticHighlightings;
import org.eclipse.wst.jsdt.internal.ui.text.AbstractJavaScanner;
import org.eclipse.wst.jsdt.internal.ui.text.CombinedWordRule;
import org.eclipse.wst.jsdt.internal.ui.text.CombinedWordRule.CharacterBuffer;
import org.eclipse.wst.jsdt.internal.ui.text.CombinedWordRule.WordMatcher;
import org.eclipse.wst.jsdt.internal.ui.text.ISourceVersionDependent;
import org.eclipse.wst.jsdt.internal.ui.text.JavaWhitespaceDetector;
import org.eclipse.wst.jsdt.ui.PreferenceConstants;
import org.eclipse.wst.jsdt.ui.text.IColorManager;
import org.eclipse.wst.jsdt.ui.text.IJavaScriptColorConstants;


public class MessagingJavaCodeScanner extends AbstractJavaScanner
{

   MessageTransformationController controller;

   private class MessageWordMatcher extends CombinedWordRule.WordMatcher
   {

      @Override
      public IToken evaluate(ICharacterScanner scanner, CharacterBuffer word)
      {
         String text = word.toString();
         String sourceFieldText = controller.getDraggedText();
         if (sourceFieldText != null)
         {
            if (text.indexOf(sourceFieldText) != -1)
            {
               Token token = getToken(IJavaScriptColorConstants.JAVA_KEYWORD);
               // Color color = new Color(Display.getDefault(),255,0,0);
               TextAttribute taOld = (TextAttribute) token.getData();
               TextAttribute ta = new TextAttribute(taOld.getForeground(), taOld
                     .getBackground(), SWT.ITALIC, taOld.getFont());
               return new Token(ta);
            }
         }
         return super.evaluate(scanner, word);
      }

   }

   private class MessageWordDetector implements IWordDetector
   {

      public boolean isWordPart(char c)
      {
         return c == '.' || Character.isJavaIdentifierPart(c);
      }

      public boolean isWordStart(char c)
      {
         return Character.isJavaIdentifierStart(c);
      }

   }

   /**
    * Rule to detect java operators.
    * 
    * @since 3.0
    */
   private static final class OperatorRule implements IRule
   {

      /** Java operators */
      private final char[] JAVA_OPERATORS = {
            ';', '.', '=', '/', '\\', '+', '-', '*', '<', '>', ':', '?', '!', ',', '|',
            '&', '^', '%', '~'};

      /** Token to return for this rule */
      private final IToken fToken;

      /**
       * Creates a new operator rule.
       * 
       * @param token
       *            Token to use for this rule
       */
      public OperatorRule(IToken token)
      {
         fToken = token;
      }

      /**
       * Is this character an operator character?
       * 
       * @param character
       *            Character to determine whether it is an operator character
       * @return <code>true</code> iff the character is an operator, <code>false</code>
       *         otherwise.
       */
      public boolean isOperator(char character)
      {
         for (int index = 0; index < JAVA_OPERATORS.length; index++)
         {
            if (JAVA_OPERATORS[index] == character)
               return true;
         }
         return false;
      }

      /*
       * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
       */
      public IToken evaluate(ICharacterScanner scanner)
      {

         int character = scanner.read();
         if (isOperator((char) character))
         {
            do
            {
               character = scanner.read();
            }
            while (isOperator((char) character));
            scanner.unread();
            return fToken;
         }
         else
         {
            scanner.unread();
            return Token.UNDEFINED;
         }
      }
   }

   /**
    * Rule to detect java brackets.
    * 
    * @since 3.3
    */
   private static final class BracketRule implements IRule
   {

      /** Java brackets */
      private final char[] JAVA_BRACKETS = {'(', ')', '{', '}', '[', ']'};

      /** Token to return for this rule */
      private final IToken fToken;

      /**
       * Creates a new bracket rule.
       * 
       * @param token
       *            Token to use for this rule
       */
      public BracketRule(IToken token)
      {
         fToken = token;
      }

      /**
       * Is this character a bracket character?
       * 
       * @param character
       *            Character to determine whether it is a bracket character
       * @return <code>true</code> iff the character is a bracket, <code>false</code>
       *         otherwise.
       */
      public boolean isBracket(char character)
      {
         for (int index = 0; index < JAVA_BRACKETS.length; index++)
         {
            if (JAVA_BRACKETS[index] == character)
               return true;
         }
         return false;
      }

      /*
       * @see org.eclipse.jface.text.rules.IRule#evaluate(org.eclipse.jface.text.rules.ICharacterScanner)
       */
      public IToken evaluate(ICharacterScanner scanner)
      {

         int character = scanner.read();
         if (isBracket((char) character))
         {
            do
            {
               character = scanner.read();
            }
            while (isBracket((char) character));
            scanner.unread();
            return fToken;
         }
         else
         {
            scanner.unread();
            return Token.UNDEFINED;
         }
      }
   }

   private static class VersionedWordMatcher extends CombinedWordRule.WordMatcher
         implements ISourceVersionDependent
   {

      private final IToken fDefaultToken;

      private final String fVersion;

      private boolean fIsVersionMatch;

      public VersionedWordMatcher(IToken defaultToken, String version,
            String currentVersion)
      {
         fDefaultToken = defaultToken;
         fVersion = version;
         setSourceVersion(currentVersion);
      }

      /*
       * @see org.eclipse.wst.jsdt.internal.ui.text.ISourceVersionDependent#setSourceVersion(java.lang.String)
       */
      public void setSourceVersion(String version)
      {
         fIsVersionMatch = fVersion.compareTo(version) <= 0;
      }

      /*
       * @see org.eclipse.wst.jsdt.internal.ui.text.CombinedWordRule.WordMatcher#evaluate(org.eclipse.jface.text.rules.ICharacterScanner,
       *      org.eclipse.wst.jsdt.internal.ui.text.CombinedWordRule.CharacterBuffer)
       */
      public IToken evaluate(ICharacterScanner scanner,
            CombinedWordRule.CharacterBuffer word)
      {
         IToken token = super.evaluate(scanner, word);
         if (fIsVersionMatch || token.isUndefined())
            return token;

         return fDefaultToken;
      }
   }

   private static final String SOURCE_VERSION = JavaScriptCore.COMPILER_SOURCE;

   static String[] fgKeywords = {"abstract", //$NON-NLS-1$
         "break", //$NON-NLS-1$
         "case", "catch", "class", "const", "continue", //$NON-NLS-5$ //$NON-NLS-4$ //$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
         "default", "delete", "debugger", "do", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ 
         "else", "export", "extends", //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-1$
         "final", "finally", "for", "function",//$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-2$ //$NON-NLS-1$
         "goto", //$NON-NLS-1$
         "if", "implements", "in", "instanceof", "interface", //$NON-NLS-5$ //$NON-NLS-4$ //$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
         "new", //$NON-NLS-1$
         "package", "private", "protected", "public", //$NON-NLS-4$ //$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
         "static", "super", "switch", "synchronized", //$NON-NLS-4$ //$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
         "this", "throw", "throws", "transient", "try", "typeof", //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-4$ //$NON-NLS-3$ //$NON-NLS-2$ //$NON-NLS-1$
         "var", "volatile", //$NON-NLS-1$ //$NON-NLS-2$
         "while", "saubloed"//$NON-NLS-1$ //$NON-NLS-2$
   };

   private static final String INTERFACE = "interface"; //$NON-NLS-1$

   private static final String RETURN = "return"; //$NON-NLS-1$

   private static String[] fgJava14Keywords = { /* "assert" */}; //$NON-NLS-1$

   private static String[] fgJava15Keywords = {"enum"}; //$NON-NLS-1$

   private static String[] fgTypes = {/*
                                        * "void", "boolean", "char", "byte", "short",
                                        * "strictfp", "int", "long", "float", "double"
                                        */}; //$NON-NLS-1$ //$NON-NLS-5$ //$NON-NLS-7$ //$NON-NLS-6$ //$NON-NLS-8$ //$NON-NLS-9$  //$NON-NLS-10$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-2$

   private static String[] fgConstants = {"false", "null", "true", "undefined"}; //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-2$ //$NON-NLS-1$

   private static final String ANNOTATION_BASE_KEY = PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_PREFIX
         + SemanticHighlightings.ANNOTATION;

   private static final String ANNOTATION_COLOR_KEY = ANNOTATION_BASE_KEY
         + PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_COLOR_SUFFIX;

   private static String[] fgTokenProperties = {
      IJavaScriptColorConstants.JAVA_KEYWORD, IJavaScriptColorConstants.JAVA_STRING,
      IJavaScriptColorConstants.JAVA_DEFAULT, IJavaScriptColorConstants.JAVA_KEYWORD_RETURN,
      IJavaScriptColorConstants.JAVA_OPERATOR, IJavaScriptColorConstants.JAVA_BRACKET,
         ANNOTATION_COLOR_KEY,};

   private List fVersionDependentRules = new ArrayList(3);

   private WordMatcher theWordRule;

   /**
    * Creates a Java code scanner
    * 
    * @param manager
    *            the color manager
    * @param store
    *            the preference store
    */
   public MessagingJavaCodeScanner(IColorManager manager, IPreferenceStore store,
         MessageTransformationController controller)
   {
      super(manager, store);
      this.controller = controller;
      initialize();
   }

   /*
    * @see AbstractJavaScanner#getTokenProperties()
    */
   protected String[] getTokenProperties()
   {
      return fgTokenProperties;
   }

   /*
    * @see AbstractJavaScanner#createRules()
    */
   protected List createRules()
   {

      List rules = new ArrayList();

      // Add rule for character constants.
      Token token = getToken(IJavaScriptColorConstants.JAVA_STRING);
      rules.add(new SingleLineRule("'", "'", token, '\\')); //$NON-NLS-2$ //$NON-NLS-1$

      // Add generic whitespace rule.
      rules.add(new WhitespaceRule(new JavaWhitespaceDetector()));

      String version = getPreferenceStore().getString(SOURCE_VERSION);

      // Add word rule for new keywords, 4077

      MessageWordDetector wordDetector = new MessageWordDetector();

      token = getToken(IJavaScriptColorConstants.JAVA_DEFAULT);
      CombinedWordRule combinedWordRule = new CombinedWordRule(wordDetector, token);

      token = getToken(IJavaScriptColorConstants.JAVA_DEFAULT);
      VersionedWordMatcher j14Matcher = new VersionedWordMatcher(token,
            JavaScriptCore.VERSION_1_4, version);

      token = getToken(IJavaScriptColorConstants.JAVA_KEYWORD);
      for (int i = 0; i < fgJava14Keywords.length; i++)
         j14Matcher.addWord(fgJava14Keywords[i], token);

      combinedWordRule.addWordMatcher(j14Matcher);
      fVersionDependentRules.add(j14Matcher);

      token = getToken(IJavaScriptColorConstants.JAVA_DEFAULT);
      VersionedWordMatcher j15Matcher = new VersionedWordMatcher(token,
            JavaScriptCore.VERSION_1_5, version);
      token = getToken(IJavaScriptColorConstants.JAVA_KEYWORD);
      for (int i = 0; i < fgJava15Keywords.length; i++)
         j15Matcher.addWord(fgJava15Keywords[i], token);

      combinedWordRule.addWordMatcher(j15Matcher);
      fVersionDependentRules.add(j15Matcher);

      // Add rule for operators
      token = getToken(IJavaScriptColorConstants.JAVA_OPERATOR);
      rules.add(new OperatorRule(token));

      // Add rule for brackets
      token = getToken(IJavaScriptColorConstants.JAVA_BRACKET);
      rules.add(new BracketRule(token));

      // Add word rule for messages
      CombinedWordRule.WordMatcher messagesRule = new MessageWordMatcher();
      token = getToken(IJavaScriptColorConstants.JAVA_KEYWORD);
      combinedWordRule.addWordMatcher(messagesRule);

      // Add word rule for keyword 'return'.
      CombinedWordRule.WordMatcher returnWordRule = new CombinedWordRule.WordMatcher();
      token = getToken(IJavaScriptColorConstants.JAVA_KEYWORD_RETURN);
      returnWordRule.addWord(RETURN, token);
      combinedWordRule.addWordMatcher(returnWordRule);

      // Add word rule for keywords, types, and constants.
      CombinedWordRule.WordMatcher wordRule = new CombinedWordRule.WordMatcher();
      token = getToken(IJavaScriptColorConstants.JAVA_KEYWORD);
      for (int i = 0; i < fgKeywords.length; i++)
         wordRule.addWord(fgKeywords[i], token);
      for (int i = 0; i < fgTypes.length; i++)
         wordRule.addWord(fgTypes[i], token);
      for (int i = 0; i < fgConstants.length; i++)
         wordRule.addWord(fgConstants[i], token);

      combinedWordRule.addWordMatcher(wordRule);

      rules.add(combinedWordRule);

      setDefaultReturnToken(getToken(IJavaScriptColorConstants.JAVA_DEFAULT));
      return rules;
   }

   /*
    * @see org.eclipse.wst.jsdt.internal.ui.text.AbstractJavaScanner#getBoldKey(java.lang.String)
    */
   protected String getBoldKey(String colorKey)
   {
      if ((ANNOTATION_COLOR_KEY).equals(colorKey))
         return ANNOTATION_BASE_KEY
               + PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_BOLD_SUFFIX;
      return super.getBoldKey(colorKey);
   }

   /*
    * @see org.eclipse.wst.jsdt.internal.ui.text.AbstractJavaScanner#getItalicKey(java.lang.String)
    */
   protected String getItalicKey(String colorKey)
   {
      if ((ANNOTATION_COLOR_KEY).equals(colorKey))
         return ANNOTATION_BASE_KEY
               + PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_ITALIC_SUFFIX;
      return super.getItalicKey(colorKey);
   }

   /*
    * @see org.eclipse.wst.jsdt.internal.ui.text.AbstractJavaScanner#getStrikethroughKey(java.lang.String)
    */
   protected String getStrikethroughKey(String colorKey)
   {
      if ((ANNOTATION_COLOR_KEY).equals(colorKey))
         return ANNOTATION_BASE_KEY
               + PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_STRIKETHROUGH_SUFFIX;
      return super.getStrikethroughKey(colorKey);
   }

   /*
    * @see org.eclipse.wst.jsdt.internal.ui.text.AbstractJavaScanner#getUnderlineKey(java.lang.String)
    */
   protected String getUnderlineKey(String colorKey)
   {
      if ((ANNOTATION_COLOR_KEY).equals(colorKey))
         return ANNOTATION_BASE_KEY
               + PreferenceConstants.EDITOR_SEMANTIC_HIGHLIGHTING_UNDERLINE_SUFFIX;
      return super.getUnderlineKey(colorKey);
   }

   /*
    * @see AbstractJavaScanner#affectsBehavior(PropertyChangeEvent)
    */
   public boolean affectsBehavior(PropertyChangeEvent event)
   {
      return event.getProperty().equals(SOURCE_VERSION) || super.affectsBehavior(event);
   }

   /*
    * @see AbstractJavaScanner#adaptToPreferenceChange(PropertyChangeEvent)
    */
   public void adaptToPreferenceChange(PropertyChangeEvent event)
   {

      if (event.getProperty().equals(SOURCE_VERSION))
      {
         Object value = event.getNewValue();

         if (value instanceof String)
         {
            String s = (String) value;

            for (Iterator it = fVersionDependentRules.iterator(); it.hasNext();)
            {
               ISourceVersionDependent dependent = (ISourceVersionDependent) it.next();
               dependent.setSourceVersion(s);
            }
         }

      }
      else if (super.affectsBehavior(event))
      {
         super.adaptToPreferenceChange(event);
      }
   }
}
