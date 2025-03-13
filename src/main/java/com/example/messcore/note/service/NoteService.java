package com.example.messcore.note.service;

import com.example.messcore.messsage.repository.ConversationRepository;
import com.example.messcore.note.dto.NoteDto;
import com.example.messcore.note.repository.NoteRepository;
import com.example.messcore.staff.repository.StaffRepository;
import ezcloud.message.messenger.Conversation;
import ezcloud.message.messenger.Note;
import ezcloud.message.staff.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class NoteService {
    private final StaffRepository staffRepository;
    private final ConversationRepository conversationRepository;
    private final NoteRepository noteRepository;

    public void saveNote(NoteDto noteDto) {
        Note note = new Note();
        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());

        if (noteDto.getStaffId() == null) {
            throw new IllegalArgumentException("StaffID not null");
        }
        Staff staff = staffRepository.findById(noteDto.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        note.setStaff(staff);

        if (noteDto.getConversationId() == null) {
            throw new IllegalArgumentException("ConversationID not null");
        }
        Conversation conversation = conversationRepository.findById(noteDto.getConversationId())
                .orElseThrow(() -> new RuntimeException("Conversation not found"));
        note.setConversation(conversation);

        noteRepository.save(note);
    }


}
