import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.awt.*;

/**
 * This is the Main Class that runs the program
 * ==========================================================
 * Note: To Read the Default File, please have a "deck.txt"
 * not in the "src" directory, but have it located in the
 * main project folder directory
 */
public class Program extends JFrame {


    /**
     * Launches the program
     * @param args
     */
    public static void main(String[] args)

    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                Program program = new Program();
                program.setVisible(true);
            }
        });

    }

    /**
     * Constructor
     */
    public Program()
    {
        createGUI();

    }

    //Prestuff
    JFrame frame1 = new JFrame("Flashcard Editor");


    /**
     * Method that puts the Components of the Interface Together
     * for the Flashcard Editor Part of the Program
     */
    void createGUI()
    {

        //Editor Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(20,50, 600, 600);
        setResizable(false);
        setTitle("Flashcard Editor");
        getContentPane().setLayout(new GridLayout(2, 1));

        //setVisible(true);

            //Panels for Editor
            JPanel questionPanel = new JPanel();
            JPanel panel1 = new JPanel();

            //Set State of each Text Area
            questionTextArea.setLineWrap(true);
            questionTextArea.setWrapStyleWord(true);
            answerTextArea.setLineWrap(true);
            answerTextArea.setWrapStyleWord(true);
            questionTextArea.setForeground(Color.BLACK);
            answerTextArea.setForeground(Color.BLACK);
            questionTextArea.setEditable(false);
            answerTextArea.setEditable(false);
            statusTextField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY));



            //Question Panel
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.PAGE_AXIS));
            questionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Question"),
            BorderFactory.createEmptyBorder(10,10,10,10)));
            questionPanel.add(questionScroll);


            //Answer panel
            JPanel answerPanel = new JPanel();
            answerPanel.setLayout(new BoxLayout(answerPanel, BoxLayout.PAGE_AXIS));
            answerPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Answer"),
                    BorderFactory.createEmptyBorder(10,10,10,10)));
            answerPanel.add(answerScroll);


        //Next Prev and find buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(prevCardButton);
        buttonPanel.add(findCardButton);
        buttonPanel.add(nextCardButton);

        //Panel for Status
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout());
        statusPanel.add(statusLabel);
        statusPanel.add(statusTextField);

            //Add Question Panel, Answer Panel, Status Panel and Button Panel to panel1
              panel1.add(questionPanel,BorderLayout.NORTH);
              panel1.add(Box.createRigidArea(new Dimension(10,0)));
              panel1.add(answerPanel,BorderLayout.NORTH);
              panel1.add(statusPanel,BorderLayout.WEST);
              panel1.add(Box.createRigidArea(new Dimension(10,10)));
              panel1.add(buttonPanel,BorderLayout.SOUTH);

             // Panel 2 for Editor
             JPanel panel2 = new JPanel();
             panel2.setLayout(new FlowLayout());
             panel2.add(addCardButton);
             panel2.add(removeCardButton);
             panel2.add(resetButton);

            //Add Panels to Frame
            getContentPane().add(panel1);
            getContentPane().add(panel2);

             //MenuBar for Editor
             mb.add(menu1);
             mb.add(menu2);
             mb.setBackground(Color.CYAN);
             menu1.add(i1); menu1.add(i2); menu1.add(i3);
             menu1.add(i4); menu1.add(i5); menu1.add(i6);
             menu2.add(b1); menu2.add(b2); menu2.add(b3);
             menu1.setMnemonic(KeyEvent.VK_A);
             menu2.setMnemonic(KeyEvent.VK_A);
             this.setJMenuBar(mb);



             //Attached Action Listeners for Editor
            i1.addActionListener(new ReadInMenuItemListener());
            i2.addActionListener(new ReadInDefaultMenuItemListener());
            i6.addActionListener(new QuitMenuItemListener());
            i3.addActionListener(new SaveAsMenuItemListener());
            i4.addActionListener(new SaveAsDefaultMenuItemListener());
            i5.addActionListener(new CreateFileListener());
            b1.addActionListener(new EditQuestionListener());
            b2.addActionListener(new EditAnswerListener());
            b3.addActionListener(new EditViewedListener());
            addCardButton.addActionListener(new AddCardButtonListener());
            removeCardButton.addActionListener(new RemoveCardButtonListener());
            resetButton.addActionListener(new ResetButtonListener());
            findCardButton.addActionListener(new FindCardButtonListener());
            nextCardButton.addActionListener(new NextCardButtonListener());
            prevCardButton.addActionListener(new PrevCardButtonListener());
            resetButton.addActionListener(new ResetButtonListener());

            //Visibility of Menus in the initial Editor
            setAllComponentsEnabled(false);
            i3.setEnabled(false);
            i4.setEnabled(false);
            b1.setEnabled(false);
            b2.setEnabled(false);
            b3.setEnabled(false);




        //Quizzer Window
        JFrame frame2 = new JFrame("Flashcard Quizzer");
        frame2.setBounds(750,50, 600, 600);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setVisible(true);



    }


    //Action Listeners for Editor

    /**
     * This class controls the Edit Question Menu
     */
    class EditQuestionListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                String newQuestion = JOptionPane.showInputDialog(getContentPane(),
                        "Please Enter a new question:");
                if (newQuestion.trim().length() == 0) throw new BlankCardException();
                if (folder.sameQuestion(newQuestion)) throw new DuplicateQuestionException();
                folder.returnCurrentCard().setQuestion(newQuestion);
                questionTextArea.setText(newQuestion);
                writeOutButton.setEnabled(true);
                questionTextArea.setLineWrap(true);
                questionTextArea.setWrapStyleWord(true);


            }
            catch (BlankCardException e) {
                JOptionPane.showMessageDialog(null,
                        "Blank question is NOT allowed! Please retry.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
            catch (DuplicateQuestionException e) {
                JOptionPane.showMessageDialog(null,
                        "Duplicate question found! Please revise.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
            catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null,
                        "You may edit the question next time.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


    /**
     * This class controls the "Edit Answer" Menu
     */
    class EditAnswerListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                String newAnswer = JOptionPane.showInputDialog(getContentPane(),
                        "Please Enter a new answer:");
                if (newAnswer.trim().length() == 0) throw new BlankCardException();
                folder.returnCurrentCard().setAnswer(newAnswer);
                answerTextArea.setForeground(Color.BLACK);
                answerTextArea.setText(newAnswer);
                writeOutButton.setEnabled(true);


            }
            catch (BlankCardException e) {
                JOptionPane.showMessageDialog(null,
                        "Blank answer is NOT allowed! Please retry.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
            }
            catch (NullPointerException e) {
                JOptionPane.showMessageDialog(null,
                        "You may edit the answer next time.",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * This Listener class controls whether the card has been viewed or not.
     * The status of the card is controlled with this class.
     */
    class EditViewedListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            Object[] options = new String[] {"The card has been Viewed", "The Card Hasn't Been Viewed"};
            int option = JOptionPane.showOptionDialog(null,
                    "Set this card as:",
                    "Set status",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (option == JOptionPane.YES_OPTION) {
                folder.returnCurrentCard().setViewed(true);
                statusTextField.setText("Yes");
            }
            if (option == JOptionPane.NO_OPTION) {
                folder.returnCurrentCard().setViewed(false);
                statusTextField.setText("No");
            }
            writeOutButton.setEnabled(true);
        }
    }


    /**
     * Action Listener Class that allows the user to select
     * "Create a Blank Deck" that creates a new blank deck file
     */
    class CreateFileListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int yesNo = JOptionPane.showConfirmDialog(
                    null,
                    "Creating a new blank deck file will overwrite the" +
                            " default deck.\nThis action cannot be reversed.\n" +
                            "Do you want to create a blank deck file?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) {
                folder.clearDeck();
                folder.save();
                setEditComponentsEnabled(true);
                readInButton.setEnabled(true);
               // saveAsMenuItem.setEnabled(true);
               // saveAsDefaultMenuItem.setEnabled(true);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
                displayBlankTextFieldContent();
                setEditButtonsEnabled(false);
                i3.setEnabled(true);
                i4.setEnabled(true);
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);
            }
        }
    }


    /**
     * Listener class for "Read File" JMenuItem that allows user to select
     * file to read in from open file dialog and all cards contained in the
     * file.
     * @exception ArrayIndexOutOfBoundsException if file format is not valid.
     * @exception NumberFormatException if file format is not valid.
     * @exception IndexOutOfBoundsException if file format
     * is not valid (blank).
     */
    class ReadInMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Select File to Read in:");
                int result = chooser.showOpenDialog(getContentPane());
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    folder.createDeck(file);
                    displayTextFieldContent();
                    setEditComponentsEnabled(true);
                    readInButton.setEnabled(true);
                    i3.setEnabled(true);
                    i4.setEnabled(true);
                    i2.setEnabled(true);
                    i1.setEnabled(true);
                    i3.setEnabled(true);
                    i4.setEnabled(true);
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid deck file format! Please retry.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid deck file format! Please retry.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
            catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid deck file format! Please retry.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    /**
     * Listener class for "Read In from Default File" Menu Item that allows
     * the user to read in from the default file.
     */
    class ReadInDefaultMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (folder.read() == 1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Read data file successfully! You can now edit" +
                                " the file.",
                        "Successful Read-in",
                        JOptionPane.INFORMATION_MESSAGE);
                setEditComponentsEnabled(true);
                displayTextFieldContent();
                readInButton.setEnabled(true);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);

                i3.setEnabled(true);
                i4.setEnabled(true);
                b1.setEnabled(true);
                b2.setEnabled(true);
                b3.setEnabled(true);

            }
            else if (folder.read() == 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "No Data file found! New data file will be" +
                                " created automatically.",
                        "Initialization Information",
                        JOptionPane.INFORMATION_MESSAGE);
                folder.save();
                setEditComponentsEnabled(true);
                displayTextFieldContent();
                readInButton.setEnabled(false);
                i3.setEnabled(true);
                i4.setEnabled(true);
                writeOutButton.setEnabled(true);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
                setEditButtonsEnabled(false);
            }
            else
                JOptionPane.showMessageDialog(
                        null,
                        "Unable to read in the data file! Please retry.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
        }
    }


    /**
     * Listener class for JMenuItem that quits the build program.
     */
    class QuitMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            System.exit(0);
        }
    }

    /**
     * Listener class for "save as" JButton that allows user to select
     * file save location of edited or new flashcard file.
     */
    class SaveAsMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Save File to Another Location:");
            int result = chooser.showSaveDialog(getContentPane());
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                folder.save(file);
            }
        }
    }


    /**
     * Listener for "Save as Default File" JButton that allows the user to
     * select to save to the default file
     */
    class SaveAsDefaultMenuItemListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            if (folder.save())
                JOptionPane.showMessageDialog(
                        null,
                        "Save data file successfully! The records have been updated.",
                        "Successful Output",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to write to file! Please retry.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Listener class for JButton that allows user to step through card deck
     * to next card.
     * @exception IndexOutOfBoundsException catches any instances where current
     * card is last card in deck (and next card does not exist).
     */
    class NextCardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                prevCardButton.setEnabled(true);
                folder.save();
                folder.setNext();
                displayTextFieldContent();
                removeCardButton.setEnabled(true);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
            }
            catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null,
                        "No Card to Select.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                nextCardButton.setEnabled(false);
                prevCardButton.setEnabled(false);
            }
        }
    }

    /**
     * Listener class for JButton that allows user to step through card deck
     * to previous card.
     * @exception IndexOutOfBoundsException catches any instances where current
     * card is first card in deck (and previous card does not exist).
     */
    class PrevCardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            try {
                nextCardButton.setEnabled(true);
                folder.save();
                folder.setPrevious();
                displayTextFieldContent();
                removeCardButton.setEnabled(true);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
            }
            catch (IndexOutOfBoundsException e) {
                JOptionPane.showMessageDialog(null,
                        "No Card to Select.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                prevCardButton.setEnabled(false);
                nextCardButton.setEnabled(false);
            }
        }
    }

    /**
     * Listener for JButton that resets status of all cards in deck to
     * virgin.
     */
    class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int yesNo = JOptionPane.showConfirmDialog(
                    null,
                    "Reset all cards to virgin status.\nThis action cannot" +
                            " be reversed.\nDo you really want to reset these cards?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) {
                folder.setNoneViewed();
                folder.sort();
                if (folder.save()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Reset all cards successfully! The records have been updated.",
                            "Successful Output",
                            JOptionPane.INFORMATION_MESSAGE);
                    setAllComponentsEnabled(false);
                    readInButton.setEnabled(true);
                    i1.setEnabled(true);
                }
                else
                    JOptionPane.showMessageDialog(
                            null,
                            "Failed to reset these cards! Please retry.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Listener class for JButton that allows user to add a newly created
     * card to the deck of flashcards.  Uses addNewCard() method for user
     * to enter new card information.
     */
    class AddCardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int yesNo = JOptionPane.showConfirmDialog(
                    null,
                    "Would you like to create a new card?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) addCard();
        }
    }

    /**
     * Listener class for JButton that allows user to remove currently
     * selected card.  Permanently deletes card information from associated
     * file after prompting user that action cannot be reversed.
     */
    class RemoveCardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int yesNo = JOptionPane.showConfirmDialog(
                    null,
                    "This action cannot be reversed. \nDo you really " +
                            "want to remove this card?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION);
            if (yesNo == JOptionPane.YES_OPTION) {
                folder.removeFlashcard(folder.returnCurrentCard());
                folder.save();
                folder.decreaseCounter();
                displayBlankTextFieldContent();
                writeOutButton.setEnabled(true);
                removeCardButton.setEnabled(false);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
            }
        }
    }

    /**
     * Listener class for JButton that allows user to find a card based on
     * whether entered text matches either the question or answer.
     * @exception Exception if no card question or answer matches
     * user-entered keyword.
     */
    class FindCardButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String keyWord = JOptionPane.showInputDialog("Enter any key words:");
            try {
                Flashcard targetCard = folder.find(keyWord);
                if (targetCard == null) throw new java.util.NoSuchElementException();
                int index = folder.getDeck().indexOf(targetCard);
                folder.setCurrent(targetCard);
                folder.specificCounter(index);
                displayTextFieldContent();
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
            }
            catch(java.util.NoSuchElementException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "No card containing keyword \'" + keyWord + "\' found!",
                        "No Result",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }



    //Put Listners for Quizzer Here



    /**
     * Displays the text field contents
     */
    private void displayTextFieldContent() {
        questionTextArea.setText(folder.returnCurrentCard().getQuestion());
        answerTextArea.setText(folder.returnCurrentCard().getAnswer());
        //intervalTextField.setText(Integer.toString(
            //    folder.returnCurrentCard().getInterval()));
       // presentationTextField.setText(Integer.toString(
              //  folder.getScheduledTime()));
        statusTextField.setText(
                folder.returnCurrentCard().getView() ? "Yes" : "No");
    }


    /**
     * Clears the Text Fields
     */
    private void displayBlankTextFieldContent() {
        questionTextArea.setText("");
        answerTextArea.setText("");
        statusTextField.setText("");
    }

    /**
     * Set all buttons and texts to enabled.
     * @param b boolean value indicating whether or not all components
     * should be enabled.
     */
    private void setAllComponentsEnabled(boolean b) {
        writeOutButton.setEnabled(b);
        prevCardButton.setEnabled(b);
        nextCardButton.setEnabled(b);
        setEditComponentsEnabled(b);
    }

    /**
     * Enable or disable components and text fields sans the previous card,
     * next card and writeOutButton.
     * @param b boolean value indicating whether or not components
     * should be enabled.
     */
    private void setEditComponentsEnabled(boolean b) {
        addCardButton.setEnabled(b);
        removeCardButton.setEnabled(b);
        findCardButton.setEnabled(b);
        resetButton.setEnabled(b);
        questionTextArea.setEnabled(b);
        answerTextArea.setEnabled(b);
        statusTextField.setEnabled(b);
        setEditButtonsEnabled(b);
    }

    /**
     * Enable or disable all edit JButtons.
     * @param b boolean value indicating whether or not all edit buttons
     * should be enabled.
     */
    private void setEditButtonsEnabled(boolean b) {
        editQuestionButton.setEnabled(b);
        editAnswerButton.setEnabled(b);
        editIntervalButton.setEnabled(b);
        editTimeButton.setEnabled(b);
        editStatusButton.setEnabled(b);
    }

    /**
     * Enable or disable next card JButton.  If current card is the
     * last card in the deck, next card button will not be enabled.
     * @param upper integer value not to be exceeded by card counter.
     */
    private void setNextButtonVisibility(int upper) {
        if (folder.getCounterStatus() >= upper)
            nextCardButton.setEnabled(false);
        else
            nextCardButton.setEnabled(true);
    }

    /**
     * Enable or disable previous card JButton.  If current card is that
     * first card in the deck, previous card button will not be enabled.
     * @param lower integer value that card counter should equal at its
     * minimum.
     */
    private void setPrevButtonVisibility(int lower) {
        if (folder.getCounterStatus() <= lower)
            prevCardButton.setEnabled(false);
        else
            prevCardButton.setEnabled(true);
    }

    /**
     * Enable or disable both previous and next card JButtons depending
     * on whether current card counter is equal to the lower and upper
     * limits, respectively.
     * @param lower integer value that card counter should equal at its
     * minimum.
     * @param upper integer value not to be exceeded by card counter.
     */
    private void setPrevNextButtonVisibility(int lower, int upper) {
        setNextButtonVisibility(upper);
        setPrevButtonVisibility(lower);
    }

    /**
     * Exception for when the question that is trying to be added is a duplicate
     */
    class DuplicateQuestionException extends Exception {
        public DuplicateQuestionException() {}
        public DuplicateQuestionException(String msg) {super(msg);}
    }

    /**
     * Exception for when a user tries to store a blank card into the deck
     */
    class BlankCardException extends Exception {
        public BlankCardException() {}
        public BlankCardException(String msg) {super(msg);}
    }


    /**
     * Adds a New Flashcard into the Deck
     * The user is prompted to input the Question and Answer
     */
    private void addCard() {
        String newQuestion = "", newAnswer = "";


        try {
            newQuestion = JOptionPane.showInputDialog(getContentPane(),
                    "Please Enter a new question:");
            if (newQuestion.trim().length() == 0) throw new BlankCardException();
            if (folder.sameQuestion(newQuestion)) throw new DuplicateQuestionException();
            newAnswer = JOptionPane.showInputDialog(getContentPane(),
                    "Please Enter a new answer:");
            if (newAnswer.trim().length() == 0) throw new BlankCardException();
            answerScroll.repaint();
            questionScroll.repaint();
            folder.addFlashcard(new Flashcard(newQuestion, newAnswer,  false));
            if (folder.save()) {
                JOptionPane.showMessageDialog(
                        null,
                        "The card was Successfully Added. The file has been updated",
                        "Successful Output",
                        JOptionPane.INFORMATION_MESSAGE);
                writeOutButton.setEnabled(true);
                setPrevNextButtonVisibility(0, folder.getDeckSize() - 1);
                folder.updateCard();
                displayTextFieldContent();
                setEditButtonsEnabled(true);
            }
            else
                JOptionPane.showMessageDialog(
                        null,
                        "This card failed to be added into the Deck. Please try again.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
        }
        catch (BlankCardException e) {
            JOptionPane.showMessageDialog(null,
                    "The input was blank. Please try again.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (DuplicateQuestionException e) {
            JOptionPane.showMessageDialog(null,
                    "Duplicate Question Detected. Please try again.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    "Nothing was added. Try again next time.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Please select your card.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }



    //IMPORTANT
    private FlashcardFolder folder = new FlashcardFolder();

    //TextArea and Field for Editor
    private JTextArea questionTextArea = new JTextArea("Select File in the Menu to Load or Create a Deck",10,20);
    private JTextArea answerTextArea = new JTextArea(10,20);
    private JTextField statusTextField = new JTextField();

    //Buttons for Editor
    private JButton prevCardButton = new JButton("Previous Card");
    private JButton nextCardButton = new JButton("Next Card");
    private JButton editQuestionButton = new JButton("Question");
    private JButton editAnswerButton = new JButton("Answer");
    private JButton editIntervalButton = new JButton("Interval");
    private JButton editTimeButton = new JButton("Scheduled Time");
    private JButton editStatusButton = new JButton("Status");
    private JButton readInButton = new JButton("Read In From File...");
    private JButton addCardButton = new JButton("Add New Card");
    private JButton findCardButton = new JButton("Find Card");
    private JButton removeCardButton = new JButton("Delete Card");
    private JButton writeOutButton = new JButton("Write Out To File...");
    private JButton resetButton = new JButton("Clear Deck");

    //Labels for Editor
    private JLabel statusLabel = new JLabel("             Been Viewed?: ");

    //Scroll Panes for Editor
    JScrollPane answerScroll = new JScrollPane(answerTextArea);;
    JScrollPane questionScroll = new JScrollPane(questionTextArea);

    //Menu Stuff for Editor
    JMenuBar mb = new JMenuBar();
    JMenu menu1 = new JMenu("File");
    JMenu menu2 = new JMenu("Edit");
    JMenuItem i1 = new JMenuItem("Read File");
    JMenuItem i2 = new JMenuItem("Read the Default File");
    JMenuItem i3 = new JMenuItem("Save As");
    JMenuItem i4 = new JMenuItem("Save to Default File");
    JMenuItem i5 = new JMenuItem("Create a Blank Deck");
    JMenuItem i6 = new JMenuItem("Quit");
    JMenuItem b1 = new JMenuItem("Edit Card Question");
    JMenuItem b2 = new JMenuItem("Edit Card Answer");
    JMenuItem b3 = new JMenuItem("Edit Card Viewed Status");


    //Put Variables for Quizzer Here



}







