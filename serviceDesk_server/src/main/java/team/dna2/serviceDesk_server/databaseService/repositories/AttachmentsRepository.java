package team.dna2.serviceDesk_server.databaseService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.dna2.serviceDesk_server.databaseService.entities.Attachment;

import java.util.List;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachment, Long> {

    List<Attachment> findAttachmentsByComment_Id(Long commentId);

    List<Attachment> findAttachmentsByTicket_Id(Long ticketId);
}
